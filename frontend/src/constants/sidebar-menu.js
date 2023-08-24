import { PiUsers } from 'react-icons/pi';
import { BiCategoryAlt, BiHomeAlt2 } from 'react-icons/bi';
import { BsCardChecklist } from 'react-icons/bs';

const sidebar_menu = [
    {
        id: 1,
        icon: BiHomeAlt2,
        path: '/admin',
        title: 'General',
    },
    {
        id: 2,
        icon: PiUsers,
        path: '/admin/profesores',
        title: 'Profesores',
    },
    {
        id: 3,
        icon: PiUsers,
        path: '/admin/estudiantes',
        title: 'Estudiantes',
    },
    {
        id: 4,
        icon: BiCategoryAlt,
        path: '/admin/categorias',
        title: 'Categorías',
    },
    {
        id: 5,
        icon: BsCardChecklist,
        path: '/admin/caracteristicas',
        title: 'Caracteristicas',
    }
]

export default sidebar_menu;