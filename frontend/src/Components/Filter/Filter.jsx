import './Filter.scss'

const Filter = ({ activeFilter, setActiveFilter, setSearchData, setTeachingProficiency, setFiltered, popular }) => {

  const handleFilterChange = (newFilter) => {
    setTeachingProficiency((prevTeachingProficiency) => ({
      ...prevTeachingProficiency,
      subject: newFilter,
    }));
    setSearchData((prevSearchData) => ({
      ...prevSearchData,
      pageSize: 100,
    }));
    setActiveFilter(newFilter);
  };

  return (
    <div className="filter-container">
      <button 
        className={activeFilter === '' ? "active" : ""}
        onClick={() => handleFilterChange('')}
      >
        Recomendados
      </button>
      <button 
        className={activeFilter === 'MATH' ? "active" : ""}
        onClick={() => handleFilterChange('MATH')}
      >
       Matemática
      </button>
      <button 
        className={activeFilter === 'ENGLISH' ? "active" : ""}
        onClick={() => handleFilterChange('ENGLISH')}
      >
        Ingles
      </button>
      <button 
        className={activeFilter === 'BIOLOGY' ? "active" : ""}
        onClick={() => handleFilterChange('BIOLOGY')}
      >
        Biologia
      </button>
      <button 
        className={activeFilter === 'HISTORY' ? "active" : ""}
        onClick={() => handleFilterChange('HISTORY')}
      >
        Historia
      </button>
      <button 
        className={activeFilter === 'LITERATURE' ? "active" : ""}
        onClick={() => handleFilterChange('LITERATURE')}
      >
        Literatura
      </button>
    </div>
  )
}

export default Filter