package com.hisun.PS;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT8888 {
    brParm PSTRGSA_BR = new brParm();
    DBParm PSTRGSA_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CD = "PYG09";
    int K_MTHS = 1;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String A1 = " ";
    short A2 = 0;
    short WK_B1 = 0;
    short WK_B2 = 0;
    short WK_B3 = 0;
    String WS_ERR_MSG = " ";
    int WS_VAL_EXP_EDDT = 0;
    int WS_CLCT_CNT = 0;
    char WS_CLCT_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    PSRRGSA PSRRGSA = new PSRRGSA();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCI8888 PSCI8888;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PSCI8888 = new PSCI8888();
        IBS.init(SCCGWA, PSCI8888);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PSCI8888);
        A2 = 0;
        T000_STARTBR_TABLE();
        if (pgmRtn) return;
        T001_READNEXT_TABLE();
        if (pgmRtn) return;
        T002_DELETE();
        if (pgmRtn) return;
        T200_ENDBR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSOT8888 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_TABLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRRGSA);
        WK_RGN_CD = PSCI8888.CD;
        WK_RGN_SEQ = PSCI8888.SEQ;
        WK_CCY = PSCI8888.CCY;
        CEP.TRC(SCCGWA, WK_RGN_CD);
        CEP.TRC(SCCGWA, WK_RGN_SEQ);
        CEP.TRC(SCCGWA, WK_CCY);
        PSTRGSA_BR.rp = new DBParm();
        PSTRGSA_BR.rp.TableName = "PSTRGSA";
        PSTRGSA_BR.rp.where = "RGN_CD = :WK_RGN_CD "
            + "AND RGN_SEQ = :WK_RGN_SEQ "
            + "AND CCY = :WK_CCY";
        PSTRGSA_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, PSRRGSA, this, PSTRGSA_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T001_READNEXT_TABLE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSRRGSA, this, PSTRGSA_BR);
        CEP.TRC(SCCGWA, PSRRGSA.KEY.RGN_CD);
        CEP.TRC(SCCGWA, PSRRGSA.KEY.CCY);
        CEP.TRC(SCCGWA, PSRRGSA.KEY.RGN_SEQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T100_REWRITE_TABLE() throws IOException,SQLException,Exception {
        PSRRGSA.KEY.RGN_SEQ = "44";
        PSTRGSA_RD = new DBParm();
        PSTRGSA_RD.TableName = "PSTRGSA";
        IBS.REWRITE(SCCGWA, PSRRGSA, PSTRGSA_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T002_DELETE() throws IOException,SQLException,Exception {
        PSTRGSA_RD = new DBParm();
        PSTRGSA_RD.TableName = "PSTRGSA";
        IBS.DELETE(SCCGWA, PSRRGSA, PSTRGSA_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T200_ENDBR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTRGSA_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
