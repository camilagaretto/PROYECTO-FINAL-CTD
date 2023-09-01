import React from 'react'
import Container from 'react-bootstrap/Container';
import { AnimatePresence, motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import Filter from '../../../Components/Filter/Filter';
import CardProduct from '../../../Components/Card/Card';
import Search from '../../../Components/Search/Search';
import PaperPlane from '../../../assets/Paper-Plane.png'
import BannerProfes from '../../../assets/banner-profes.svg'
import './Home.css'
import { Link } from 'react-router-dom';

const Home = () => {
  const [filtered, setFiltered] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [activeFilter, setActiveFilter] = useState('');
  const [teachingProficiency, setTeachingProficiency] = useState({
    subject: '',
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
        let shuffledItems = []
        if(teachers.searchResults) {
          shuffledItems = shuffleArray(teachers.searchResults).slice(0,9);
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
      pageSize: 10,
    }
    // const userToken = localStorage.getItem("user");
    // const tokenObj = JSON.parse(userToken);
    // const token = tokenObj?.token;

    try {
        const response = await fetch('http://localhost:8080/v1/categories/1/providers/teaching_subject/search', {
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
    <main id='home'>
      <Container>
        <section className='container-main-banner'>
          <div className='banner-description'>
            <h1>Encuentra tu nueva pasión</h1>
            <Search />
          </div>
        </section>

        <section>
          <div className='container-aprender'>
            <div>
              <h2>¿Qué te gustaría aprender hoy?</h2>
              <p>Te dejamos los tags para que encuentres los profesores recomendados!</p>
            </div>
            <Link to="/" className='container-aprender-ver'>Ver Listado Completo</Link>
          </div>

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
          <div className='mostrar-container'>
            <p>No encontraste a tu profe?</p>
            <Link className='btn btn-primary' to={`/search/${activeFilter}/null`} >
              <img className='paper-plane' src={PaperPlane} alt="Avion de papel" />
              Mostrar Todos
            </Link>
          </div>
        </section>

        <section className='container-profes'>
          <img src={BannerProfes} alt="Appkademy banner profesores" />
          <div>
            <h3>Quieres unirte al equipo de profes?</h3>
            <Link to='/' className='btn btn-profesores'>Llenar Formulario</Link>
          </div>
        </section>

      </Container>
    </main>
  )
}

export default Home