import React, {useState, useEffect} from 'react';
import DashboardHeader from '../../../Components/Admin/DashboardHeader';
import all_students from '../../../constants/students';
import {calculateRange, sliceData} from '../../../utils/table-pagination';

function Students () {
    const [search, setSearch] = useState('');
    const [students, setStudents] = useState(all_students);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);

    useEffect(() => {
        setPagination(calculateRange(all_students, 6));
        setStudents(sliceData(all_students, page, 6));
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
        setStudents(sliceData(all_students, new_page, 5));
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
                            <th>Verificado</th>
                            <th>Categoría</th>
                            <th>C. Likes</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    {students.length !== 0 ?
                        <tbody>
                            {students.map((student, index) => (
                                <tr key={index}>
                                    <td><span>{student.firstName} {student.lastName}</span></td>
                                    <td><span>{student.identityVerified ? 'Yes' : 'No'}</span></td>
                                    <td><span>{student.providerCategoryId}</span></td>
                                    <td><span>{student.totalLikes}</span></td>
                                    <td><span>Eliminar</span></td>
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