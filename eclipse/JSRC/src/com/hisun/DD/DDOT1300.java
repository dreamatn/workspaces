package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1300 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DDOT1300_WS_OUT_INF WS_OUT_INF = new DDOT1300_WS_OUT_INF();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCPELGP DDCPELGP = new DDCPELGP();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    DDB1300_AWA_1300 DDB1300_AWA_1300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT1300 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1300_AWA_1300>");
        DDB1300_AWA_1300 = (DDB1300_AWA_1300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_TRANS_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1300_AWA_1300.FUNC);
        if (DDB1300_AWA_1300.FUNC == '1') {
            B200_TRANS_DATA_PROC_1();
            if (pgmRtn) return;
        }
    }
    public void B200_TRANS_DATA_PROC_1() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        R000_STARTBR_PARM();
        if (pgmRtn) return;
        R000_READNXT_PARM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'L') {
            WS_CNT += 1;
            CEP.TRC(SCCGWA, "AAAAA");
            CEP.TRC(SCCGWA, WS_CNT);
            IBS.init(SCCGWA, DDCPELGP);
            CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
            CEP.TRC(SCCGWA, BPRPARM.BLOB_VAL);
            DDCPELGP.KEY.CD = BPRPARM.KEY.CODE;
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, DDCPELGP.DATA_TXT);
            CEP.TRC(SCCGWA, DDCPELGP.DATA_TXT.ACII);
            CEP.TRC(SCCGWA, DDCPELGP.DATA_TXT.AC);
            CEP.TRC(SCCGWA, DDCPELGP.DATA_TXT.FLG);
            R000_ACII_TRAC_PROC();
            if (pgmRtn) return;
            if (AICPQMIB.RC.RC_CODE == 0) {
                CEP.TRC(SCCGWA, "SUCCESSFULL !!!");
                CEP.TRC(SCCGWA, DDCPELGP.DATA_TXT.ACII);
                CEP.TRC(SCCGWA, DDCPELGP.DATA_TXT.AC);
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
            }
            R000_READNXT_PARM();
            if (pgmRtn) return;
        }
        R000_ENDBR_PARM();
        if (pgmRtn) return;
        R000_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 19;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_INF);
        SCCMPAG.DATA_LEN = 19;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_STARTBR_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = "PDD20";
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void R000_READNXT_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void R000_ACII_TRAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        CEP.TRC(SCCGWA, DDCPELGP.DATA_TXT.ACII);
        AICPQMIB.INPUT_DATA.AC = DDCPELGP.DATA_TXT.ACII;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
        if (AICPQMIB.RC.RC_CODE == 0 
            && AICPQMIB.OUTPUT_DATA.CBAL != 0) {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQMIB.INPUT_DATA.AC;
            AICUUPIA.DATA.RVS_SEQ = 0;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.AMT = AICPQMIB.OUTPUT_DATA.CBAL;
            WS_OUT_INF.WS_OUT_BAL = WS_OUT_INF.WS_OUT_BAL + AICPQMIB.OUTPUT_DATA.CBAL;
            AICUUPIA.DATA.CCY = "156";
            AICUUPIA.DATA.EVENT_CODE = "DR";
            AICUUPIA.DATA.POST_NARR = " ";
            AICUUPIA.DATA.EVENT_CODE = "DR";
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DDCPELGP.DATA_TXT.AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            AICUUPIA.DATA.PAY_MAN = CICQACRI.O_DATA.O_AC_CNM;
            AICUUPIA.DATA.THEIR_AC = DDCPELGP.DATA_TXT.AC;
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.BV_TYP = '3';
            DDCUCRAC.AC = DDCPELGP.DATA_TXT.AC;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.CCY_TYPE = '1';
            DDCUCRAC.TX_AMT = AICPQMIB.OUTPUT_DATA.CBAL;
            DDCUCRAC.OTHER_AC = AICPQMIB.INPUT_DATA.AC;
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CALL AIZPQMIB ERR !");
            CEP.TRC(SCCGWA, AICPQMIB.RC);
            CEP.TRC(SCCGWA, DDCPELGP.DATA_TXT.ACII);
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC.RC_CODE);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
