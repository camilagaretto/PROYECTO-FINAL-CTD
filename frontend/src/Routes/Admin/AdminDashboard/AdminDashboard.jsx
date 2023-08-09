import React from 'react'
import Graphics from '../../../assets/graficas-admin.svg'


const AdminDashboard = () => {

  return (
    <main>
        <section className='dashboard__container'>
          <img src={Graphics} alt="Panel de administracion" />
        </section>
    </main>
  )
}

export default AdminDashboard