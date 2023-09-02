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

    Axios.post('http://localhost:8080/v1/categories/1/providers/search', newObject)
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