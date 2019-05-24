package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUCBLH {
    String JIBS_tmp_str[] = new String[10];
    brParm DDTBALH_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CAL_DT1 = 0;
    int WS_CAL_DT2 = 0;
    int WS_DAYS = 0;
    int WS_TOT_DAYS = 0;
    double WS_SUM_BAL = 0;
    int WS_SUM_DAYS = 0;
    double WS_AVE_BAL = 0;
    char WS_BALH_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRBALH DDRBALH = new DDRBALH();
    CICQACAC CICQACAC = new CICQACAC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    String WS_AC = " ";
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUCBLH DDCUCBLH;
    public void MP(SCCGWA SCCGWA, DDCUCBLH DDCUCBLH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUCBLH = DDCUCBLH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUCBLH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCBLH.I_DATA.AC);
        CEP.TRC(SCCGWA, DDCUCBLH.I_DATA.STR_DT);
        CEP.TRC(SCCGWA, DDCUCBLH.I_DATA.END_DT);
        B001_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B002_CAL_AVER_DAYBAL();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUCBLH.I_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCUCBLH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUCBLH.I_DATA.STR_DT == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDATE_M_INPUT, DDCUCBLH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUCBLH.I_DATA.END_DT == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_ENDDATE_M_INPUT, DDCUCBLH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUCBLH.I_DATA.STR_DT > DDCUCBLH.I_DATA.END_DT) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_TODT_LESS_FODT, DDCUCBLH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B002_CAL_AVER_DAYBAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDCUCBLH.I_DATA.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        R000_GET_TOT_DAYS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRBALH);
        WS_AC = DDCUCBLH.I_DATA.AC;
        WS_STR_DT = DDCUCBLH.I_DATA.STR_DT;
        WS_END_DT = DDCUCBLH.I_DATA.END_DT;
        T000_STARTBR_DDTBALH();
        if (pgmRtn) return;
        T000_READNEXT_DDTBALH();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (DDRBALH.KEY.STR_DATE > WS_STR_DT) {
                WS_CAL_DT1 = DDRBALH.KEY.STR_DATE;
            } else {
                WS_CAL_DT1 = WS_STR_DT;
            }
            if (DDRBALH.END_DATE < WS_END_DT) {
                WS_CAL_DT2 = DDRBALH.END_DATE;
            } else {
                WS_CAL_DT2 = WS_END_DT;
            }
            R000_GET_BAL_DAYS();
            if (pgmRtn) return;
            WS_SUM_BAL = WS_SUM_BAL + DDRBALH.BAL * WS_DAYS;
            WS_SUM_DAYS = WS_SUM_DAYS + WS_DAYS;
            T000_READNEXT_DDTBALH();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTBALH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_SUM_BAL);
        CEP.TRC(SCCGWA, WS_TOT_DAYS);
        CEP.TRC(SCCGWA, WS_SUM_DAYS);
        if (WS_SUM_DAYS > 0) {
            WS_AVE_BAL = WS_SUM_BAL / WS_SUM_DAYS;
        }
        DDCUCBLH.O_DATA.TOT_DAYS = WS_SUM_DAYS;
        DDCUCBLH.O_DATA.AVE_BAL = WS_AVE_BAL;
        CEP.TRC(SCCGWA, WS_AVE_BAL);
        CEP.TRC(SCCGWA, WS_SUM_BAL);
        CEP.TRC(SCCGWA, WS_TOT_DAYS);
    }
    public void R000_GET_BAL_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_CAL_DT1;
        SCCCLDT.DATE2 = WS_CAL_DT2;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DAYS = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, WS_DAYS);
    }
    public void R000_GET_TOT_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = DDCUCBLH.I_DATA.STR_DT;
        SCCCLDT.DATE2 = DDCUCBLH.I_DATA.END_DT;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_TOT_DAYS = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, WS_TOT_DAYS);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUCBLH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_STARTBR_DDTBALH() throws IOException,SQLException,Exception {
        DDTBALH_BR.rp = new DBParm();
        DDTBALH_BR.rp.TableName = "DDTBALH";
        DDTBALH_BR.rp.where = "AC = :WS_AC "
            + "AND STR_DATE <= :WS_END_DT "
            + "AND END_DATE >= :WS_STR_DT";
        DDTBALH_BR.rp.order = "END_DATE DESC";
        IBS.STARTBR(SCCGWA, DDRBALH, this, DDTBALH_BR);
    }
    public void T000_READNEXT_DDTBALH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRBALH, this, DDTBALH_BR);
    }
    public void T000_ENDBR_DDTBALH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTBALH_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "PROGRAM END");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
