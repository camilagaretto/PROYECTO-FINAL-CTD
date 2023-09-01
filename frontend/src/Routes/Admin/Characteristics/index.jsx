import React, { useState, useEffect } from 'react';
import DashboardHeader from '../../../Components/Admin/DashboardHeader';
import { calculateRange, sliceData } from '../../../utils/table-pagination';
import { Link } from 'react-router-dom';
import { BiMessageSquareAdd } from 'react-icons/bi';
import { FaTrash } from 'react-icons/fa';

function Characteristics() {
    const [search, setSearch] = useState('');
    const [characteristics, setCharacteristics] = useState([]);
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
            const response = await fetch('http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/characteristics/search', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(searchData),
            });
            if (response.ok) {
                const characteristics = await response.json();
                setCharacteristics(characteristics.searchResults)
            } else {
                alert('Error al crear usuario');
            }
        } catch (error) {
            console.error('Error al obtener los datos:', error);
        }
    };

    useEffect(() => {
        setPagination(calculateRange(characteristics, 6));
        setCharacteristics(sliceData(characteristics, page, 6));
        fetchData();
    }, []);

    const __handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let search_results = characteristics.filter((item) =>
                item.name.toLowerCase().includes(search.toLowerCase())
            );
            setCharacteristics(search_results);
        }
        else {
            __handleChangePage(1);
        }
    };
    const __handleChangePage = (new_page) => {
        setPage(new_page);
        setCharacteristics(sliceData(characteristics, new_page, 5));
    };
    const __handleDelete = async (id) => {
        const userToken = localStorage.getItem("user");
        const tokenObj = JSON.parse(userToken);
        const token = tokenObj.token;

        if (window.confirm('¿Estás seguro que deseas eliminar?')) {
            try {
                const response =  await fetch(`http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/characteristics/${id}`, {
                    method: 'DELETE',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                })
                if (response.ok) {
                    alert('Característica eliminada exitosamente');
                    fetchData();
                } else {
                    alert('Error al eliminar característica');
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
                    <h2>Caracteristicas</h2>
                    <div className='dashboard-content-search'>
                        <input
                            type='text'
                            value={search}
                            placeholder='Search..'
                            className='dashboard-content-input'
                            onChange={e => __handleSearch(e)} />
                    </div>
                    <div className='mt-3'>
                        <Link className='btn btn-dark' to="/admin/agregar-caracteristica">Nueva característica <BiMessageSquareAdd/></Link>
                    </div>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    {characteristics.length !== 0 ?
                        <tbody>
                            {characteristics.map((characteristic, index) => (
                                <tr key={index}>
                                    <td><span>{characteristic.id}</span></td>
                                    <td><span>{characteristic.name}</span></td>
                                    <td className='action-buttons'>
                                        <button onClick={() => __handleDelete(characteristic.id)}><FaTrash/></button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                        : null}
                </table>

                {characteristics.length !== 0 ?
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

export default Characteristics;