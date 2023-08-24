import React, { useState, useEffect } from 'react';
import DashboardHeader from '../../../Components/Admin/DashboardHeader';
import { calculateRange, sliceData } from '../../../utils/table-pagination';
import { Link } from 'react-router-dom';

function Categories() {
    const [search, setSearch] = useState('');
    const [categories, setCategories] = useState([]);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);

    const fetchData = async () => {
        const searchData = {
            pageNumber: 1,
            pageSize: 10,
        }

        try {
            const response = await fetch('http://localhost:8080/v1/categories/1/providers/teaching_subject/search', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(searchData),
            });
            if (response.ok) {
                const categories = await response.json();
                setCategories(categories.searchResults)
            } else {
                alert('Error al obtener categorías');
            }
        } catch (error) {
            console.error('Error al obtener los datos:', error);
        }
    };

    useEffect(() => {
        setPagination(calculateRange(categories, 6));
        setCategories(sliceData(categories, page, 6));
        fetchData();
    }, []);

    const __handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let search_results = categories.filter((item) =>
                item.name.toLowerCase().includes(search.toLowerCase())
            );
            setCategories(search_results);
        }
        else {
            __handleChangePage(1);
        }
    };

    const __handleChangePage = (new_page) => {
        setPage(new_page);
        setCategories(sliceData(categories, new_page, 5));
    }

    return (
        <div className='dashboard-content'>
            <DashboardHeader />
            <div className='dashboard-content-container'>
                <div className='dashboard-content-header'>
                    <h2>Categorías</h2>
                    <Link className='btn btn-dark' to="/admin/agregar-categoria">Nueva categoría</Link>
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
                            <th>Id</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>

                    {categories.length !== 0 ?
                        <tbody>
                            {categories.map((category, index) => (
                                <tr key={index}>
                                    <td><span>{category.id}</span></td>
                                    <td><span>{category.name}</span></td>
                                </tr>
                            ))}
                        </tbody>
                        : null}
                </table>

                {categories.length !== 0 ?
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

export default Categories;