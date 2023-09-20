import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import './Search.scss'
import { useNavigate } from 'react-router-dom';
import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';

const Search = ({ subject, dateTime}) => {

    const navigate = useNavigate()
    const [inputOptions, setInputOptions] = useState([])


    const [searchData, setSearchData] = useState({
        subject: subject ? subject : "",
        freeOn: dateTime ? dateTime : ""
    })

    const handleChange = (e) => {
        setSearchData({ ...searchData, [e.target.name]: e.target.value })
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if (searchData.freeOn && searchData.subject) {
            navigate(`/search/${searchData.subject}/${searchData.freeOn}`)
        } else if (!searchData.subject) {
            navigate(`/`)
        } else {
            navigate(`/search/${searchData.subject}/null`)
        }
    }

    const getCategories = async () => {
        const postData = {
          pageNumber: 1,
          pageSize: 10,
        }
        try {
          const response = await fetch('http://ec2-174-129-118-14.compute-1.amazonaws.com/v1/categories/1/providers/teaching_subject/search', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'accept' : 'application/json'
            },
            body: JSON.stringify(postData),
          });
          if (response.ok) {
            const subjects = await response.json();
            const categoryNames = subjects.searchResults.map((category) => category.name);
            setInputOptions(categoryNames);
          } else {
            console.log(response)
          }
        } catch (error) {
          console.error('Error de red:', error);
        }
      };

    useEffect(()=>{
        getCategories()
    },[])

    return (
        <InputGroup size="md" className='search__input'>
            <Typeahead
                value={searchData.subject}
                id="autocomplete-input"
                onChange={(selected) => {
                    if (selected.length > 0) {
                        setSearchData({ ...searchData, subject: selected[0] });
                    } else {
                        setSearchData({ ...searchData, subject: '' });
                    }
                }}
                options={inputOptions}
                selected={searchData.subject ? [searchData.subject] : []}
                placeholder="¿Qué te gustaría aprender?"
            />
            <Form.Control value={searchData.freeOn} onChange={handleChange} name='freeOn' className='form-control-date' type="datetime-local" />
            <Button onClick={onSubmit} variant='primary'>Buscar</Button>
        </InputGroup>
    )
}

export default Search