import React from 'react'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import './Search.css'

const Search = () => {
    return (
        <InputGroup size="lg">
            <Form.Control
            aria-label="Large"
            aria-describedby="inputGroup-sizing-sm"
            />
            <InputGroup.Text id="inputGroup-sizing-lg">Large</InputGroup.Text>
        </InputGroup>
    )
}

export default Search