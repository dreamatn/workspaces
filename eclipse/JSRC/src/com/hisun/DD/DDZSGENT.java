package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSGENT {
    DBParm DDTMST_RD;
    DBParm DDTAGENT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD891";
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    DDCOGENT DDCOGENT = new DDCOGENT();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRAGENT DDRAGENT = new DDRAGENT();
    CICPDTL CICPDTL = new CICPDTL();
    SCCGWA SCCGWA;
    DDCSGENT DDCSGENT;
    public void MP(SCCGWA SCCGWA, DDCSGENT DDCSGENT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSGENT = DDCSGENT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSGENT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSGENT.FUNC);
        CEP.TRC(SCCGWA, DDCSGENT.CUS_AC);
        CEP.TRC(SCCGWA, DDCSGENT.LMT_CCY);
        CEP.TRC(SCCGWA, DDCSGENT.LMT_BAL);
        CEP.TRC(SCCGWA, DDCSGENT.AGID_TYP);
        CEP.TRC(SCCGWA, DDCSGENT.AGID_NO);
        CEP.TRC(SCCGWA, DDCSGENT.AGID_NM);
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_CHCK_AC_CI_INF();
        if (pgmRtn) return;
        if (DDCSGENT.FUNC == 'Q') {
            B300_INQ_GENT_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSGENT.FUNC == 'M') {
            B400_MOD_GENT_REC_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCSGENT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSGENT.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSGENT.CUS_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSGENT.FUNC == 'M') {
            if (DDCSGENT.AGID_TYP.trim().length() == 0) {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CUS_TYP_MISSING;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSGENT.AGID_NO.trim().length() == 0) {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_AG_ID_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSGENT.AGID_NM.trim().length() == 0) {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CHCK_AC_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSGENT.CUS_AC;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        R000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_INF_NOTFND);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            if (DDRMST.AC_STS == 'C') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_INQ_GENT_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRAGENT);
        DDRAGENT.KEY.CUS_AC = DDCSGENT.CUS_AC;
        T000_READ_DDTAGENT();
        if (pgmRtn) return;
        B310_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B400_MOD_GENT_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRAGENT);
        DDRAGENT.KEY.CUS_AC = DDCSGENT.CUS_AC;
        T000_READUPDATE_DDTAGENT();
        if (pgmRtn) return;
        if (DDRAGENT.STS != '0') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CI_HAS_ADULT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDCSGENT.LMT_CCY.equalsIgnoreCase(DDRAGENT.LMT_CCY) 
            || DDCSGENT.LMT_BAL != DDRAGENT.LMT_BAL) {
            B410_REG_LMT_CIZPDTL();
            if (pgmRtn) return;
        }
        DDRAGENT.LMT_CCY = DDCSGENT.LMT_CCY;
        DDRAGENT.LMT_BAL = DDCSGENT.LMT_BAL;
        DDRAGENT.AGID_TYP = DDCSGENT.AGID_TYP;
        DDRAGENT.AGID_NO = DDCSGENT.AGID_NO;
        DDRAGENT.AGID_NM = DDCSGENT.AGID_NM;
        DDRAGENT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRAGENT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DDRAGENT.LMT_CCY);
        CEP.TRC(SCCGWA, DDRAGENT.LMT_BAL);
        CEP.TRC(SCCGWA, DDRAGENT.AGID_TYP);
        CEP.TRC(SCCGWA, DDRAGENT.AGID_NO);
        CEP.TRC(SCCGWA, DDRAGENT.AGID_NM);
        CEP.TRC(SCCGWA, DDRAGENT.UPDTBL_DATE);
        CEP.TRC(SCCGWA, DDRAGENT.UPDTBL_TLR);
        R000_REWRITE_DDTAGENT();
        if (pgmRtn) return;
        B310_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B310_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOGENT);
        DDCOGENT.FUNC = DDCSGENT.FUNC;
        DDCOGENT.CUS_AC = DDRAGENT.KEY.CUS_AC;
        DDCOGENT.AGID_TYP = DDRAGENT.AGID_TYP;
        DDCOGENT.AGID_NO = DDRAGENT.AGID_NO;
        DDCOGENT.AGID_NM = DDRAGENT.AGID_NM;
        DDCOGENT.LMT_CCY = DDRAGENT.LMT_CCY;
        DDCOGENT.LMT_BAL = DDRAGENT.LMT_BAL;
        CEP.TRC(SCCGWA, DDCOGENT.FUNC);
        CEP.TRC(SCCGWA, DDCOGENT.CUS_AC);
        CEP.TRC(SCCGWA, DDCOGENT.AGID_TYP);
        CEP.TRC(SCCGWA, DDCOGENT.AGID_NO);
        CEP.TRC(SCCGWA, DDCOGENT.AGID_NM);
        CEP.TRC(SCCGWA, DDCOGENT.LMT_CCY);
        CEP.TRC(SCCGWA, DDCOGENT.LMT_BAL);
        CEP.TRC(SCCGWA, "************OUTPUT************");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOGENT;
        SCCFMT.DATA_LEN = 382;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B410_REG_LMT_CIZPDTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = '1';
        CICPDTL.TYPE = "05";
        CICPDTL.LC_OBJ = DDRAGENT.KEY.CUS_AC;
        CICPDTL.CUS_AC = DDRAGENT.KEY.CUS_AC;
        CICPDTL.TX_TYPE = "01";
        CICPDTL.LVL = 33;
        CICPDTL.LC_CCY = DDCSGENT.LMT_CCY;
        CICPDTL.DAY_STA = SCCGWA.COMM_AREA.AC_DATE;
        CICPDTL.DAY_END = 99991231;
        CICPDTL.DLY_AMT = DDCSGENT.LMT_BAL;
        CICPDTL.TXN_AMT = DDCSGENT.LMT_BAL;
        CICPDTL.DLY_VOL = 99999;
        CICPDTL.MLY_AMT = 99999999999999.99;
        CICPDTL.MLY_VOL = 99999;
        CICPDTL.SYY_AMT = 99999999999999.99;
        CICPDTL.YLY_AMT = 99999999999999.99;
        CICPDTL.TM_AMT = 99999999999999.99;
        CICPDTL.SE_AMT = 99999999999999.99;
        CICPDTL.LMT_AMT1 = 99999999999999.99;
        CICPDTL.LMT_AMT2 = 99999999999999.99;
        CICPDTL.LMT_AMT3 = 99999999999999.99;
        CICPDTL.LMT_AMT4 = 99999999999999.99;
        CICPDTL.BAL_AMT = 99999999999999.99;
        CICPDTL.LMT_STSW = "EEEEEEEEEEEEEEE";
        CICPDTL.TIMES50[1-1].CON_TYP1 = 17;
        CICPDTL.TIMES50[1-1].VAL = "0";
        CICPDTL.TIMES50[2-1].CON_TYP1 = 17;
        CICPDTL.TIMES50[2-1].VAL = "1";
        S000_CALL_CIZPDTL();
        if (pgmRtn) return;
    }
    public void R000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
    }
    public void T000_READUPDATE_DDTAGENT() throws IOException,SQLException,Exception {
        DDTAGENT_RD = new DBParm();
        DDTAGENT_RD.TableName = "DDTAGENT";
        DDTAGENT_RD.where = "CUS_AC = :DDRAGENT.KEY.CUS_AC";
        DDTAGENT_RD.upd = true;
        IBS.READ(SCCGWA, DDRAGENT, this, DDTAGENT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_INF_NOTFND);
        }
    }
    public void R000_REWRITE_DDTAGENT() throws IOException,SQLException,Exception {
        DDTAGENT_RD = new DBParm();
        DDTAGENT_RD.TableName = "DDTAGENT";
        IBS.REWRITE(SCCGWA, DDRAGENT, DDTAGENT_RD);
    }
    public void T000_READ_DDTAGENT() throws IOException,SQLException,Exception {
        DDTAGENT_RD = new DBParm();
        DDTAGENT_RD.TableName = "DDTAGENT";
        IBS.READ(SCCGWA, DDRAGENT, DDTAGENT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_INF_NOTFND);
        }
    }
    public void S000_CALL_CIZPDTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PDTL", CICPDTL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
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
