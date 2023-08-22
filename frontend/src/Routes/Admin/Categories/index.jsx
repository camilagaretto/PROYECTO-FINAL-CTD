import React, {useState, useEffect} from 'react';
import DashboardHeader from '../../../Components/Admin/DashboardHeader';
import all_categories from '../../../constants/categories';
import {calculateRange, sliceData} from '../../../utils/table-pagination';

function Categories () {
    const [search, setSearch] = useState('');
    const [categories, setCategories] = useState(all_categories);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);

    useEffect(() => {
        setPagination(calculateRange(all_categories, 6));
        setCategories(sliceData(all_categories, page, 6));
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
        setCategories(sliceData(all_categories, new_page, 5));
    }

    return(
        <div className='dashboard-content'>
            <DashboardHeader/>

            <div className='dashboard-content-container'>
                <div className='dashboard-content-header'>
                    <h2>Categorías</h2>
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