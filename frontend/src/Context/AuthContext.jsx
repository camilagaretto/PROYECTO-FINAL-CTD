import { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export function useAuth() {
  return useContext(AuthContext);
}

export function AuthProvider({ children }) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  const login = (userData) => {
    setIsLoggedIn(true);
    setIsAdmin(userData.isAdmin || false);
  };

  const logout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem('user');
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, isAdmin, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}