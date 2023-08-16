import React from 'react'
import { Container } from 'react-bootstrap'
import UserForm from '../../../Components/UserForm/UserForm'
import GifForm from '../../../Components/GifForm/GifForm'
import './RegisterUser.scss'

const RegisterUser = () => {
  return (
    <main>
        <Container>
            <section className='register-container'>
                <UserForm/>
                <GifForm title="Aprende de los mejores"/>
            </section>
        </Container>
    </main>
  )
}

export default RegisterUser