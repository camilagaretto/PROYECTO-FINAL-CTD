import React, {useState, useEffect} from 'react';
import DashboardHeader from '../../../Components/Admin/DashboardHeader';
import {calculateRange, sliceData} from '../../../utils/table-pagination';

function Students () {
    const [search, setSearch] = useState('');
    const [students, setStudents] = useState([]);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);

    const fetchData = async () => {
        const userToken = localStorage.getItem("user");
        const tokenObj = JSON.parse(userToken);
        const token = tokenObj.token; 

        const searchData = {
            "pageNumber": 1,
            "pageSize": 10,
        }

        try {
          const response = await fetch('http://localhost:8080/v1/categories/1/customers/search', {
            method:'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(searchData),
            });
            if (response.ok) {
                const students = await response.json();
                setStudents(students.searchResults)
            } else {
                console.log(response)
                alert('Error al crear usuario');
            }
        } catch (error) {
          console.error('Error al obtener los datos:', error);
        }
    };

    useEffect(() => {
        setPagination(calculateRange(students, 6));
        setStudents(sliceData(students, page, 6));
        fetchData();
    }, []);

    const __handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let search_results = students.filter((item) =>
                item.firstName.toLowerCase().includes(search.toLowerCase()) ||
                item.lastName.toLowerCase().includes(search.toLowerCase())
            );
            setStudents(search_results);
        }
        else {
            __handleChangePage(1);
        }
    };

    const __handleChangePage = (new_page) => {
        setPage(new_page);
        setStudents(sliceData(students, new_page, 5));
    }
    const __handleRol = async (student) => {
        const userToken = localStorage.getItem("user");
        const tokenObj = JSON.parse(userToken);
        const token = tokenObj.token;
        const dataRol = {
            roleIds: [
              2
            ]
        }

        try {
            const response = await fetch(`http://localhost:8080/v1/auth/${student.userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(dataRol),
            });
            if (response.ok) {
                alert(`${student.firstName} ahora es admin`);
                fetchData();
            } else {
                alert('Error al asignar rol');
            }
        } catch (error) {
            console.error('Error de red:', error);
        }
    }

    return(
        <div className='dashboard-content'>
            <DashboardHeader/>

            <div className='dashboard-content-container'>
                <div className='dashboard-content-header'>
                    <h2>Estudiantes</h2>
                    <div className='dashboard-content-search'>
                        <input
                            type='text'
                            value={search}
                            placeholder='Search..'
                            className='dashboard-content-input'
                            onChange={e => __handleSearch(e)} />
                    </div>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Usuario</th>
                            <th>Habilitado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    {students.length !== 0 ?
                        <tbody>
                            {students.map((student, index) => (
                                <tr key={index}>
                                    <td><span>{student.firstName} {student.lastName}</span></td>
                                    <td><span>{student.enabled ? 'Yes' : 'No'}</span></td>
                                    <td>  <button onClick={() => __handleRol(student)}>Hacer admin</button></td>
                                </tr>
                            ))}
                        </tbody>
                    : null}
                </table>

                {students.length !== 0 ?
                    <div className='dashboard-content-footer'>
                        {pagination.map((item, index) => (
                            <span 
                                key={index} 
                                className={item === page ? 'active-pagination' : 'pagination'}
                                onClick={() => __handleChangePage(item)}>
                                    {item}
                            </span>
                        ))}
                    </div>
                : 
                    <div className='dashboard-content-footer'>
                        <span className='empty-table'>No data</span>
                    </div>
                }
            </div>
        </div>
    )
}

export default Students;