#!/bin/sh
cd "$(dirname "$0")"
npm install
npm run build
cd dist
tar czfv cliente.tar.gz *
cp cliente.tar.gz /app/modulo_2/nginx
