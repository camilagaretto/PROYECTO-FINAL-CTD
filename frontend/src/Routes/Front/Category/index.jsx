import React from 'react'
import Container from 'react-bootstrap/Container';
import { AnimatePresence, motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import Filter from '../../../Components/Filter/Filter';
import CardProduct from '../../../Components/Card/Card';
import './styles.css'
import { Link } from 'react-router-dom';

const Category = () => {
  const [filtered, setFiltered] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [activeFilter, setActiveFilter] = useState('');
  const [teachingProficiency, setTeachingProficiency] = useState({
    subject: '',
    masteryLevel: ''
  });
  const [searchData, setSearchData] = useState({
    pageNumber: 1,
    pageSize: 100,
  });
  const fetchData = async () => {

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
    }
  };


  useEffect(() => {
    getCategories();
    fetchData();
  }, [activeFilter]);

  return (
    <main>
      <Container>
        <section className=''>
            <h1>Todos nuestros profesores</h1>
        </section>

        <section>
        <Filter
            categories={subjects}
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
            {filtered.length > 0 ? (
                  filtered.map(teacher => (
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
      </Container>
    </main>
  )
}

export default Category