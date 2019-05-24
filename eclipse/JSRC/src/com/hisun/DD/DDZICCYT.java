package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZICCYT {
    DBParm DDTCCYT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    char WS_CCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRCCYT DDRCCYT = new DDRCCYT();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCICCYT DDCICCYT;
    public void MP(SCCGWA SCCGWA, DDCICCYT DDCICCYT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCICCYT = DDCICCYT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZICCYT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCICCYT.DATA.AC);
        CEP.TRC(SCCGWA, DDCICCYT.DATA.CCY);
        CEP.TRC(SCCGWA, DDCICCYT.DATA.CCY_TYPE);
        B005_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B010_QUERY_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B005_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCICCYT.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCICCYT.DATA.AC;
        CICQACAC.DATA.CCY_ACAC = DDCICCYT.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DDCICCYT.DATA.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCYT);
        DDRCCYT.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCYT.KEY.AC);
        T000_READ_DDTCCYT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DDCICCYT);
            DDCICCYT.DATA.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDCICCYT.DATA.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
            DDCICCYT.DATA.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
            DDCICCYT.DATA.ADP_AVE_BAL = DDRCCYT.ADP_AVE_BAL;
            DDCICCYT.DATA.ADP_TOT_BAL = DDRCCYT.ADP_TOT_BAL;
            DDCICCYT.DATA.MON_TOT_BAL = DDRCCYT.MON_TOT_BAL;
            DDCICCYT.DATA.LAST_BAL = DDRCCYT.LAST_BAL;
            DDCICCYT.DATA.LAST_LOC_BAL = DDRCCYT.LAST_LOC_BAL;
            DDCICCYT.DATA.LAST_MON_TOL_BAL1 = DDRCCYT.LAST_MON_TOL_BAL1;
            DDCICCYT.DATA.LAST_MON_TOL_BAL2 = DDRCCYT.LAST_MON_TOL_BAL2;
            DDCICCYT.DATA.LAST_MON_TOL_BAL3 = DDRCCYT.LAST_MON_TOL_BAL3;
            DDCICCYT.DATA.LAST_MON_TOL_BAL4 = DDRCCYT.LAST_MON_TOL_BAL4;
            DDCICCYT.DATA.LAST_MON_TOL_BAL5 = DDRCCYT.LAST_MON_TOL_BAL5;
            DDCICCYT.DATA.LAST_MON_TOL_BAL6 = DDRCCYT.LAST_MON_TOL_BAL6;
            DDCICCYT.DATA.LAST_MON_TOL_BAL7 = DDRCCYT.LAST_MON_TOL_BAL7;
            DDCICCYT.DATA.LAST_MON_TOL_BAL8 = DDRCCYT.LAST_MON_TOL_BAL8;
            DDCICCYT.DATA.LAST_MON_TOL_BAL9 = DDRCCYT.LAST_MON_TOL_BAL9;
            DDCICCYT.DATA.LAST_MON_TOL_BAL10 = DDRCCYT.LAST_MON_TOL_BAL10;
            DDCICCYT.DATA.LAST_MON_TOL_BAL11 = DDRCCYT.LAST_MON_TOL_BAL11;
            DDCICCYT.DATA.LAST_MON_TOL_BAL12 = DDRCCYT.LAST_MON_TOL_BAL12;
            DDCICCYT.DATA.LAST_MON_AVE_BAL = DDRCCYT.LAST_MON_AVE_BAL;
            DDCICCYT.DATA.LAST_3MON_AVE_BAL = DDRCCYT.LAST_3MON_AVE_BAL;
            DDCICCYT.DATA.LAST_6MON_AVE_BAL = DDRCCYT.LAST_6MON_AVE_BAL;
            DDCICCYT.DATA.LAST_12MON_AVE_BAL = DDRCCYT.LAST_12MON_AVE_BAL;
            DDCICCYT.DATA.LAST_MON_BAL = DDRCCYT.LAST_MON_BAL;
            DDCICCYT.DATA.LAST_3MON_BAL = DDRCCYT.LAST_3MON_BAL;
            DDCICCYT.DATA.LAST_6MON_BAL = DDRCCYT.LAST_6MON_BAL;
            DDCICCYT.DATA.LAST_12MON_BAL = DDRCCYT.LAST_12MON_BAL;
            DDCICCYT.DATA.LAST_YEAR_BAL = DDRCCYT.LAST_YEAR_BAL;
            CEP.TRC(SCCGWA, DDCICCYT.DATA.AC);
            CEP.TRC(SCCGWA, DDCICCYT.DATA.CCY);
            CEP.TRC(SCCGWA, DDCICCYT.DATA.CCY_TYPE);
        }
    }
    public void T000_READ_DDTCCYT() throws IOException,SQLException,Exception {
        DDTCCYT_RD = new DBParm();
        DDTCCYT_RD.TableName = "DDTCCYT";
        IBS.READ(SCCGWA, DDRCCYT, DDTCCYT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCICCYT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCICCYT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCICCYT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCICCYT=");
            CEP.TRC(SCCGWA, DDCICCYT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
