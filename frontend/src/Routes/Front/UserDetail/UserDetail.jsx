import React, { useEffect } from 'react'
import { useAuth } from '../../../Context/AuthContext'
import { useNavigate } from 'react-router-dom'
import UserUpdateForm from '../../../Components/UserForm/UserUpdateForm'
import { Container } from 'react-bootstrap'
import './UserDetail.scss'

const UserDetail = () => {

    const { isLoggedIn } = useAuth()
    const navigate = useNavigate()

    useEffect(() => {
        if (!isLoggedIn) {
            navigate('/')
        }
    }, [])



    return (
        <main>
            <Container className='userDetail-container'>
                    <UserUpdateForm />
            </Container>
        </main>
    )
}

export default UserDetail