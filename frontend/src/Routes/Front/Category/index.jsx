import React, { useEffect, useState } from 'react'
import { Container } from 'react-bootstrap'
import { Link, useParams } from 'react-router-dom'
import Axios from 'axios'
import CardProduct from '../../../Components/Card/Card'
import { AnimatePresence, motion } from 'framer-motion';
import './styles.css'
import Search from '../../../Components/Search/Search'

const index = () => {

  const { subject, dateTime } = useParams()
  const [teachers, setTeachers] = useState([])

  useEffect(() => {

<<<<<<< frontend/src/Routes/Front/Category/index.jsx
    try {
      const response = await fetch('http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/search', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
      });
      if (response.ok) {
        const teachers = await response.json();
        let shuffledItems = []
        if(teachers.searchResults) {
          shuffledItems = teachers.searchResults;
        }
        setFiltered(shuffledItems);
      } else {
        console.log(response)
      }
    } catch (error) {
      console.error('Error de red:', error);
    }
  }
  const getCategories = async () => {
    const postData = {
      pageNumber: 1,
      pageSize: 100,
    }      

    try {
        const response = await fetch('http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/teaching_subject/search', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(postData),
        });
        if (response.ok) {
          const subjects = await response.json();
          setSubjects(subjects.searchResults);
        } else {
          console.log(response)
        }
    } catch (error) {
        console.error('Error de red:', error);
=======
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
>>>>>>> frontend/src/Routes/Front/Category/index.jsx
    }

    Axios.post('http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/search', newObject)
      .then(res => setTeachers(res.data.searchResults))

  }, [subject, dateTime])

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