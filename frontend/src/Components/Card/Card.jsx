import React, { useState } from 'react'
import './Card.scss'
import { motion } from "framer-motion"
import { Link } from 'react-router-dom'
import { useAuth } from '../../Context/AuthContext'

const Card = ({ teacher }) => {

    const [isFavourite, setIsFavourite] = useState(false);
    const { isLoggedIn } = useAuth()

    const handleAddFavourite = async () => {
        
        const userDataJSON = localStorage.getItem('user');
        if (userDataJSON) {
            const userData = JSON.parse(userDataJSON);
            const { token, userTypeId } = userData
            const requestBody = {
                likedTeacherId : teacher.id
            }
            try {
                const response = await fetch(`http://localhost:8080/v1/categories/1/customers/${userTypeId}`, {
                    method: 'PATCH',
                    headers: {
                        'accept': 'application/json',
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    },
                    body : JSON.stringify(requestBody)
                })
                if(response.ok){
                    alert('Profesor agregado a favorito!');
                    setIsFavourite(!isFavourite);
                } else {
                    alert('Error al guardar en favorito')
                }
            } catch (error) {
                console.error('Error de red:', error);
            }
        }
    }

    return (
        <motion.div
            layout
            animate={{ opacity: 1 }}
            initial={{ opacity: 0 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.5 }}
        >
            <div className='card__container' style={{ backgroundImage: `url(${teacher.profilePictureUrl})` }}>
                {isLoggedIn && <h4 onClick={handleAddFavourite} className='fav' style={{ color: isFavourite ? 'red' : 'white' }}>♥</h4>}
                <Link className='card-link' to={`/teacher/${teacher.id}`} >
                    <div >
                        <p><span>{teacher.shortDescription}</span></p>
                        <p>{teacher.firstName} {teacher.lastName}</p>
                    </div>
                </Link>
            </div>
        </motion.div>
    )
}

export default Card