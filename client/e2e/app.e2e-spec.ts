import { OtimizacaoClientPage } from './app.po';

describe('otimizacao-client App', () => {
  let page: OtimizacaoClientPage;

  beforeEach(() => {
    page = new OtimizacaoClientPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
