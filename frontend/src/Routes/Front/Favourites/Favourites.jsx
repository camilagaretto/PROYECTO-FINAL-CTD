import React, { useEffect, useState } from 'react'
import { Container } from 'react-bootstrap'
import { useParams } from 'react-router-dom'
import { AnimatePresence, motion } from 'framer-motion';
import CardProduct from '../../../Components/Card/Card'
import './Favourites.scss'

const Favourites = () => {

    const [likedTeacherId, setLikedTeacherId] = useState([])

    const { id } = useParams()
    const [likedTeachers, setLikedTeachers] = useState([])

    const toggleFavourite = (teacherId) => {
        // Copia la lista de profesores favoritos actual
        const updatedLikedTeachers = [...likedTeachers];

        // Verifica si el profesor ya está en favoritos
        const index = likedTeachers.findIndex((teacher) => teacher.id === teacherId);

        if (index !== -1) {
            // Si el profesor está en favoritos, quítalo
            updatedLikedTeachers.splice(index, 1);
        } else {
            // Si el profesor no está en favoritos, agrégalo
            const teacherToAdd = likedTeachers.find((teacher) => teacher.id === teacherId);
            if (teacherToAdd) {
                updatedLikedTeachers.push(teacherToAdd);
            }
        }

        // Actualiza el estado con la nueva lista de favoritos
        setLikedTeachers(updatedLikedTeachers);
    };
    const getStudent = async (token) => {
        const response = await fetch(`http://localhost:8080/v1/categories/1/customers/${id}`, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
        if (response.ok) {
            const data = await response.json()
            const likedTeachers = data.likedProviderIds;
            setLikedTeacherId(likedTeachers)
            if(likedTeachers.length > 0) {
                const teacherObj = {
                    pageNumber: 1,
                    pageSize: 100,
                    teacherIds: likedTeachers
                }
                const teacherResponse = await fetch('http://localhost:8080/v1/categories/1/providers/search', {
                    method: 'POST',
                    mode: 'cors',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(teacherObj)
                })
                if (teacherResponse.ok) {
                    const data = await teacherResponse.json()
                    const teacherData = data.searchResults;
                    if(teacherData.length > 0) {
                        setLikedTeachers(teacherData);
                    } else {
                        setLikedTeachers([])
                    }
                }
            }
        }
    }

    useEffect(() => {
        const userDataJSON = localStorage.getItem('user');
        if (userDataJSON) {
            const userData = JSON.parse(userDataJSON);
            const { token } = userData
            getStudent(token)
        }
    }, [id])

    return (
        <main id='favourites'>
            <Container>
                <h1>Hola, estos son tus Favoritos!</h1>
                <motion.div
                    layout
                    className="grid-container"
                >
                    <AnimatePresence>
                        {likedTeachers.length > 0 ? (
                            likedTeachers.map(teacher => (
                                <div key={teacher.id}>
                                    <CardProduct
                                        teacher={teacher}
                                        onToggleFavourite={toggleFavourite}
                                        ids={likedTeacherId}
                                    />
                                </div>
                            ))
                        ) : (
                            <p>No se encontraron resultados.</p>
                        )}
                    </AnimatePresence>
                </motion.div>
            </Container>
        </main>
    )
}

export default Favourites