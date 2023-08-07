import React from 'react'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import './Search.scss'

const Search = () => {
    return (
        <InputGroup size="lg" className='search__input'>
            <Form.Control placeholder='¿Qué deseas Aprender?'/>
            <Button variant='primary'>🔍</Button>
        </InputGroup>
    )
}

export default Search