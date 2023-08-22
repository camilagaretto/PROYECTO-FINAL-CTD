import React, {useState, useEffect} from 'react';
import DashboardHeader from '../../../Components/Admin/DashboardHeader';
import all_features from '../../../constants/features';
import {calculateRange, sliceData} from '../../../utils/table-pagination';

function Features () {
    const [search, setSearch] = useState('');
    const [features, setFeatures] = useState(all_features);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);

    useEffect(() => {
        setPagination(calculateRange(all_features, 6));
        setFeatures(sliceData(all_features, page, 6));
    }, []);

    const __handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let search_results = features.filter((item) =>
                item.name.toLowerCase().includes(search.toLowerCase())
            );
            setFeatures(search_results);
        }
        else {
            __handleChangePage(1);
        }
    };

    const __handleChangePage = (new_page) => {
        setPage(new_page);
        setFeatures(sliceData(all_features, new_page, 5));
    }

    return(
        <div className='dashboard-content'>
            <DashboardHeader/>

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
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>

                    {features.length !== 0 ?
                        <tbody>
                            {features.map((feature, index) => (
                                <tr key={index}>
                                    <td><span>{feature.id}</span></td>
                                    <td><span>{feature.name}</span></td>
                                </tr>
                            ))}
                        </tbody>
                    : null}
                </table>

                {features.length !== 0 ?
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

export default Features;