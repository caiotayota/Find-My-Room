import { createContext, useState } from 'react';
import { Auth } from '../models/Auth';

type AuthContextType = {
  auth: any;
  setAuth: any;
};

type AuthProviderType = {
  children: React.ReactNode;
};

const AuthContext = createContext({} as AuthContextType);

export const AuthProvider = ({ children }: AuthProviderType) => {
  const [auth, setAuth] = useState<Auth>();

  return (
    <AuthContext.Provider value={{ auth, setAuth }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
