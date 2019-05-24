package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIQAPL {
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTINTB_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    double WS_BAL = 0;
    double WS_INT = 0;
    short WS_CNT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINTB DDRINTB = new DDRINTB();
    DDCOIBAL DDCOIBAL = new DDCOIBAL();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCIQAPL DDCIQAPL;
    public void MP(SCCGWA SCCGWA, DDCIQAPL DDCIQAPL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIQAPL = DDCIQAPL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIQAPL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_READ_DDTMST();
        if (pgmRtn) return;
        B030_GET_DD_BAL();
        if (pgmRtn) return;
        B040_GET_INT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "**********INPUT DATA:");
        CEP.TRC(SCCGWA, DDCIQAPL.AC);
        CEP.TRC(SCCGWA, DDCIQAPL.CCY);
        CEP.TRC(SCCGWA, DDCIQAPL.CCY_TYPE);
        CEP.TRC(SCCGWA, "*********************");
        if (DDCIQAPL.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCIQAPL.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCIQAPL.CCY.equalsIgnoreCase("CNY") 
            && DDCIQAPL.CCY_TYPE != '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_READ_DDTMST() throws IOException,SQLException,Exception {
        R000_CHK_OUTPUT_AC();
        if (pgmRtn) return;
    }
    public void B030_GET_DD_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "1111");
            WS_BAL = 0;
        } else {
            WS_BAL = DDRCCY.CURR_BAL;
            DDCIQAPL.BRH_OLD = DDRCCY.OWNER_BRDP;
            DDCIQAPL.PROD_TYPE = DDRCCY.PROD_CODE;
            if (DDRCCY.STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, WS_BAL);
        for (WS_CNT = 1; WS_CNT <= 76; WS_CNT += 1) {
            if (WS_CNT == 1) {
                DDCIQAPL.BAL_INFO[WS_CNT-1].BAL = WS_BAL;
            }
        }
        CEP.TRC(SCCGWA, DDCIQAPL.BAL_INFO[1-1].BAL);
    }
    public void B040_GET_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        WS_CNT = 0;
        DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "2222");
            WS_INT = 0;
        } else {
            WS_INT = DDRINTB.DEP_ACCU_INT;
        }
        CEP.TRC(SCCGWA, DDRINTB.DEP_ACCU_INT);
        CEP.TRC(SCCGWA, WS_INT);
        for (WS_CNT = 1; WS_CNT <= 76; WS_CNT += 1) {
            if (WS_CNT == 3) {
                DDCIQAPL.BAL_INFO[WS_CNT-1].BAL = WS_INT;
            }
        }
        CEP.TRC(SCCGWA, DDCIQAPL.BAL_INFO[3-1].BAL);
    }
    public void R000_CHK_OUTPUT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCIQAPL.AC;
        CICQACAC.DATA.CCY_ACAC = DDCIQAPL.CCY;
        CICQACAC.DATA.CR_FLG = DDCIQAPL.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.col = "DEP_ACCU_INT";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
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
