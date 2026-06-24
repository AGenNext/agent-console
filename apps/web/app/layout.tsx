import './globals.css';

export const metadata = {
  title: 'AGenNext Agent Console',
  description: 'Internal hPanel-style console for AGenNext operations',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
