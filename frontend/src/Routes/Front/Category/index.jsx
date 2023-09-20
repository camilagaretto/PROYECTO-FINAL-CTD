import React, { useEffect, useState } from 'react'
import { Container } from 'react-bootstrap'
import { Link, useParams } from 'react-router-dom'
import Axios from 'axios'
import CardProduct from '../../../Components/Card/Card'
import { AnimatePresence, motion } from 'framer-motion';
import './styles.css'
import Search from '../../../Components/Search/Search'
import { useAuth } from '../../../Context/AuthContext';

const index = () => {
  const { isLoggedIn } = useAuth()
  const { subject, dateTime } = useParams()
  const [teachers, setTeachers] = useState([])
  const [likedTeachers, setLikedTeachers] = useState([])

  useEffect(() => {

    const newObject = {
      pageNumber: 1,
      pageSize: 10,
      teachingProficiency: {
        subject: {
          name: subject.toUpperCase()
        },
      },
      freeOn: dateTime,
      randomOrder: true
    }

    Axios.post('http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/search', newObject)
      .then(res => setTeachers(res.data.searchResults))

  }, [subject, dateTime])
  const getTeacherIds = async () => {
    if (isLoggedIn) {
      const userDataJSON = localStorage.getItem('user');
      const userData = JSON.parse(userDataJSON);
      const { token, userTypeId } = userData;
      const response = await fetch(`http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/customers/${userTypeId}`, {
        method: 'GET',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        }
      })
      if(response.ok){
        const data = await response.json();
        const likedTeachers = data.likedProviderIds;
        setLikedTeachers(likedTeachers)
      }
    } else {
      console.log('No se puede buscar ids de profes porque no esta logeado')
    }
  }
  useEffect(() => {
    getTeacherIds()
  }, [isLoggedIn])
  console.log(subject, dateTime)
  return (
    <main id='search'>
      <section className='search-container'>
      <Search subject={subject} time={dateTime}/>
      <h1 className='search-title'>Todos Nuestros <br /> <span>Profesores</span></h1>
        <motion.div
          layout
          className="grid-container"
        >
          <AnimatePresence>
            {teachers && teachers.length > 0 ? (
              teachers.map(teacher => (
                <Link className='card-link' key={teacher.id} to={`/teacher/${teacher.id}`} >
                  <CardProduct
                    key={teacher.id}
                    teacher={teacher}
                    ids={likedTeachers}
                  />
                </Link>
              ))
            ) : (
              <p>No se encontraron resultados.</p>
            )}
          </AnimatePresence>
        </motion.div>
      </section>
    </main>
  )
}

export default index
