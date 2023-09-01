import React, { useState, useEffect } from 'react';
import DashboardHeader from '../../../Components/Admin/DashboardHeader';
import { calculateRange, sliceData } from '../../../utils/table-pagination';
import { Link } from 'react-router-dom';
import { FaTrash } from 'react-icons/fa'
import { BiPencil, BiMessageSquareAdd } from 'react-icons/bi';


function Teachers() {
    const [search, setSearch] = useState('');
    const [teachers, setTeachers] = useState([]);
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
            const response = await fetch('http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/search', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(searchData),
            });
            if (response.ok) {
                const teachers = await response.json();
                setTeachers(teachers.searchResults)
            } else {
                console.log(response)
                alert('Error al crear usuario');
            }
        } catch (error) {
            console.error('Error al obtener los datos:', error);
        }
    };

    useEffect(() => {
        setPagination(calculateRange(teachers, 6));
        setTeachers(sliceData(teachers, page, 6));
        fetchData();
    }, []);

    const __handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let search_results = teachers.filter((item) =>
                item.firstName.toLowerCase().includes(search.toLowerCase()) ||
                item.lastName.toLowerCase().includes(search.toLowerCase())
            );
            setTeachers(search_results);
        }
        else {
            __handleChangePage(1);
        }
    };
    const __handleChangePage = (new_page) => {
        setPage(new_page);
        setTeachers(sliceData(teachers, new_page, 5));
    };
    const __handleDelete = async (id) => {
        const userToken = localStorage.getItem("user");
        const tokenObj = JSON.parse(userToken);
        const token = tokenObj.token;

        if (window.confirm('¿Estás seguro que deseas eliminar?')) {
            try {
                const response =  await fetch(`http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                })
                if (response.ok) {
                    alert('Usuario eliminado exitosamente');
                    fetchData();
                } else {
                    alert('Error al eliminar usuario');
                }
            } catch (error) {
                console.error('Error de red:', error);
            }
        }
    };

    return (
        <div className='dashboard-content'>
            <DashboardHeader />
            <div className='dashboard-content-container'>
                <div className='dashboard-content-header'>
                    <h2>Profesores</h2>
                    <div className='dashboard-content-search'>
                        <input
                            type='text'
                            value={search}
                            placeholder='Search..'
                            className='dashboard-content-input'
                            onChange={e => __handleSearch(e)} />
                    </div>
                    <div className='mt-3'>
                        <Link className='btn btn-dark' to="/admin/agregar-profesor">Nuevo profesor <BiMessageSquareAdd/></Link>
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

                    {teachers.length !== 0 ?
                        <tbody>
                            {teachers.map((teacher, index) => (
                                <tr key={index}>
                                    <td><span>{teacher.firstName} {teacher.lastName}</span></td>
                                    <td><span>{teacher.identityVerified ? 'Yes' : 'No'}</span></td>
                                    <td><span>{teacher.providerCategoryId}</span></td>
                                    <td><span>{teacher.totalLikes}</span></td>
                                    <td className='action-buttons'>
                                        <Link key={teacher.id} to={`/admin/editar-profesor/${teacher.id}`}><BiPencil/></Link>
                                        <button onClick={() => __handleDelete(teacher.id)}><FaTrash/></button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                        : null}
                </table>

                {teachers.length !== 0 ?
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

export default Teachers;