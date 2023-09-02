import React, { useState } from 'react'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import './Search.scss'
import { useNavigate } from 'react-router-dom';

const Search = ({ subject, dateTime }) => {

    const navigate = useNavigate()

    const [searchData, setSearchData] = useState({
        subject: subject ? subject : "",
        freeOn: dateTime ? dateTime : ""
    })

    const handleChange = (e) => {
        setSearchData({ ...searchData, [e.target.name]: e.target.value })
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if(searchData.freeOn && searchData.subject) {
            navigate(`/search/${searchData.subject}/${searchData.freeOn}`)
        }else if(!searchData.subject) {
            navigate(`/`)
        }else{
            navigate(`/search/${searchData.subject}/null`)

        }

    }

    return (
        <InputGroup size="md" className='search__input'>
                <Form.Control value={searchData.subject} onChange={handleChange} name='subject' placeholder='¿Qué te gustaria aprender?' />
                <Form.Control value={searchData.freeOn} onChange={handleChange} name='freeOn' className='form-control-date' type="datetime-local" />
                <Button onClick={onSubmit} variant='primary'>Buscar</Button>
        </InputGroup>
    )
}

export default Search