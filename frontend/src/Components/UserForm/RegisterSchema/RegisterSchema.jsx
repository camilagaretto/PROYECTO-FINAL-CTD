import * as Yup from 'yup'

const RegisterSchema = Yup.object().shape({
    firstName : Yup.string().required("El nombre es obligatorio"),
    lastName : Yup.string().required("El apellido es obligatorio"),
    email : Yup.string().email("Ingrese un email valido").required("El email es obligatorio"),
    password : Yup.string().required("La contraseña es obligatoria").min(7, "La contraseña debe tener al menos 7 caracteres"),
    confirmPassword : Yup.string().oneOf([Yup.ref('password'), null], 'Las contraseñas no coinciden').required("Confirme la contraseña")
})

export default RegisterSchema;