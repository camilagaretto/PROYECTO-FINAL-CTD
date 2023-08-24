import React from 'react'
import Container from 'react-bootstrap/Container';
import { AnimatePresence, motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import Filter from '../../../Components/Filter/Filter';
import CardProduct from '../../../Components/Card/Card';
import './styles.css'
import { Link } from 'react-router-dom';

const Category = () => {
  const [popular, setPopular] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [activeFilter, setActiveFilter] = useState('');
  const [teachingProficiency, setTeachingProficiency] = useState({
    subject: '',
    masteryLevel: ''
  });
  const [searchData, setSearchData] = useState({
    pageNumber: 1,
    pageSize: 30,
  });
  const shuffleArray = (array) => {
    function compareRandom(a, b) {
      return Math.random() - 0.5;
    }
    return array.sort(compareRandom);
  }
  const fetchPopular = async () => {

    const postData = {
      pageNumber: searchData.pageNumber,
      pageSize: searchData.pageSize,
    };
  
    if (teachingProficiency.subject !== '') {
      postData.teachingProficiency = {
        subject: teachingProficiency.subject,
      };
    }

    if (teachingProficiency.masteryLevel !== '') {
      postData.teachingProficiency = {
        masteryLevel: teachingProficiency.masteryLevel,
      };
    }

    try {
      const response = await fetch('http://localhost:8080/v1/categories/1/providers/search', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
      });
      if (response.ok) {
        const teachers = await response.json();
        const shuffledItems = shuffleArray(teachers.searchResults).slice(0,9);
        setPopular(shuffledItems);
        setFiltered(shuffledItems);
      } else {
        console.log(response)
      }
    } catch (error) {
      console.error('Error de red:', error);
    }
  }

  useEffect(() => {
    fetchPopular();
  }, [activeFilter]);

  return (
    <main>
      <Container>
        <section className=''>
            <h1>Todos nuestros profesores</h1>
        </section>

        <section>
          <Filter
            popular={popular}
            setFiltered={setFiltered}
            setSearchData={setSearchData}
            activeFilter={activeFilter}
            setActiveFilter={setActiveFilter}
            setTeachingProficiency={setTeachingProficiency}
          />
          <motion.div
            layout
            className="grid-container"
          >
            <AnimatePresence>
              {filtered.map(teacher => (
                <Link className='card-link' key={teacher.id} to={`/teacher/${teacher.id}`} >
                  <CardProduct
                    key={teacher.id}
                    teacher={teacher}
                  />
                </Link>
              ))}
            </AnimatePresence>
          </motion.div>
        </section>
      </Container>
    </main>
  )
}

export default Category