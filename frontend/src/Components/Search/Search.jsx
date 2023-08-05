import React from 'react'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import './Search.css'

const Search = () => {
    return (
        <InputGroup size="lg">
            <Form.Control
            aria-label="Large"
            aria-describedby="inputGroup-sizing-sm"
            />
            <Button variant='light'>🔍</Button>
        </InputGroup>
    )
}

export default Search