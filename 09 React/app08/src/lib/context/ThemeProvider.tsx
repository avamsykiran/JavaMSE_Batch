import { createContext, useEffect, useState, type ReactNode } from "react";

type Theme = "light" | "dark";

interface ThemeContextType {
    theme: Theme;
    toggleTheme: () => void;
}

export const ThemeContext = createContext<ThemeContextType | undefined>(undefined);

export default function ThemeProvider({ children }: { children: ReactNode }) {

    const [theme, setTheme] = useState<Theme>("light");

    const updateBootstrap = () => {
        document.documentElement.setAttribute('data-bs-theme', theme);
    };

    useEffect(updateBootstrap, []);
    useEffect(updateBootstrap, [theme]);

    /*
    useEffect(() => {
        document.documentElement.setAttribute('data-bs-theme', theme);
    }, []);

    useEffect(() => {
        document.documentElement.setAttribute('data-bs-theme', theme);
    }, [theme]);
    */
   
    const toggleTheme = () => {
        setTheme((prev) => (prev === 'light' ? 'dark' : 'light'));
    };

    return (
        <ThemeContext.Provider value={{ theme, toggleTheme }}>
            {children}
        </ThemeContext.Provider>
    );
}