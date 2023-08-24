import './Filter.scss'

const Filter = ({ activeFilter, setActiveFilter, setSearchData, setTeachingProficiency, categories}) => {

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
        onClick={() => handleFilterChange('')}> Recomendados </button>
      {categories.map(subject => (
        <button 
        key={subject.id}
        className={activeFilter === subject.name ? "active" : ""}
        onClick={() => handleFilterChange(subject.name)}> {subject.name} </button>
      ))}
    </div>
  )
}

export default Filter