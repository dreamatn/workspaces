package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5890 {
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSSTSW DDCSSTSW = new DDCSSTSW();
    CICACCU CICACCU = new CICACCU();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDB5890_AWA_5890 DDB5890_AWA_5890;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5890 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5890_AWA_5890>");
        DDB5890_AWA_5890 = (DDB5890_AWA_5890) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CHECK_TYPE();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.FUNC);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.AC_NO);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.AC_NM);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.REF_NO);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.TYPE);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.STS);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.EFF_DATE);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.EXP_DATE);
        CEP.TRC(SCCGWA, DDB5890_AWA_5890.REASON);
        if (DDB5890_AWA_5890.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5890_AWA_5890.FUNC != '0' 
            && DDB5890_AWA_5890.FUNC != '1' 
            && DDB5890_AWA_5890.FUNC != '2' 
            && DDB5890_AWA_5890.FUNC != '3') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            if (DDB5890_AWA_5890.FUNC == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DDB5890_AWA_5890.FUNC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5890_AWA_5890.AC_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            if (DDB5890_AWA_5890.AC_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5890_AWA_5890.AC_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5890_AWA_5890.FUNC == '0') {
            if (DDB5890_AWA_5890.REF_NO != 0) {
                DDB5890_AWA_5890.FUNC = '1';
            }
        }
        if (DDB5890_AWA_5890.FUNC == '0') {
            if (DDB5890_AWA_5890.REF_NO != 0) {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_REFNO_CANT_INP;
                WS_FLD_NO = (short) DDB5890_AWA_5890.REF_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (DDB5890_AWA_5890.FUNC == '2') {
            if (DDB5890_AWA_5890.STS != 'N') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_STS_INPUT_ERR;
                WS_FLD_NO = (short) DDB5890_AWA_5890.REF_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (DDB5890_AWA_5890.FUNC == '1' 
            || DDB5890_AWA_5890.FUNC == '3') {
            if (DDB5890_AWA_5890.REF_NO == 0) {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_REFNO_MUST_INP;
                WS_FLD_NO = (short) DDB5890_AWA_5890.REF_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_CHECK_TYPE() throws IOException,SQLException,Exception {
        if (DDB5890_AWA_5890.TYPE.equalsIgnoreCase("14") 
            || DDB5890_AWA_5890.TYPE.equalsIgnoreCase("24") 
            || DDB5890_AWA_5890.TYPE.equalsIgnoreCase("22")) {
            B210_CHCK_AC_CI_INF();
            if (pgmRtn) return;
        }
        if (DDB5890_AWA_5890.TYPE.equalsIgnoreCase("25") 
            || DDB5890_AWA_5890.TYPE.equalsIgnoreCase("26") 
            || DDB5890_AWA_5890.TYPE.equalsIgnoreCase("17")) {
            B220_CHCK_AC_FRM_APP();
            if (pgmRtn) return;
        }
    }
    public void B210_CHCK_AC_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDB5890_AWA_5890.AC_NO;
        R000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        if (DDRMST.AC_STS == 'C') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            if (DDB5890_AWA_5890.AC_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5890_AWA_5890.AC_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'V') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            if (DDB5890_AWA_5890.AC_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5890_AWA_5890.AC_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDRMST.CI_TYP == '1') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUSTBE_COM_AC;
            if (DDB5890_AWA_5890.AC_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5890_AWA_5890.AC_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void B220_CHCK_AC_FRM_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDB5890_AWA_5890.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
        if (DDB5890_AWA_5890.TYPE.equalsIgnoreCase("25") 
            || DDB5890_AWA_5890.TYPE.equalsIgnoreCase("26")) {
            if (!CICACCU.DATA.FRM_APP.equalsIgnoreCase("DD")) {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_FRM_APP_ERR;
                if (DDB5890_AWA_5890.TYPE.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(DDB5890_AWA_5890.TYPE);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (DDB5890_AWA_5890.TYPE.equalsIgnoreCase("17")) {
            if (!CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_FRM_APP_ERR;
                if (DDB5890_AWA_5890.TYPE.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(DDB5890_AWA_5890.TYPE);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSSTSW);
        DDCSSTSW.FUNC = DDB5890_AWA_5890.FUNC;
        DDCSSTSW.AC_NO = DDB5890_AWA_5890.AC_NO;
        DDCSSTSW.AC_NM = DDB5890_AWA_5890.AC_NM;
        DDCSSTSW.REF_NO = DDB5890_AWA_5890.REF_NO;
        DDCSSTSW.TYPE = DDB5890_AWA_5890.TYPE;
        DDCSSTSW.STS = DDB5890_AWA_5890.STS;
        DDCSSTSW.EFF_DATE = DDB5890_AWA_5890.EFF_DATE;
        DDCSSTSW.EXP_DATE = DDB5890_AWA_5890.EXP_DATE;
        DDCSSTSW.REASON = DDB5890_AWA_5890.REASON;
        CEP.TRC(SCCGWA, DDCSSTSW.FUNC);
        CEP.TRC(SCCGWA, DDCSSTSW.AC_NO);
        CEP.TRC(SCCGWA, DDCSSTSW.AC_NM);
        CEP.TRC(SCCGWA, DDCSSTSW.REF_NO);
        CEP.TRC(SCCGWA, DDCSSTSW.TYPE);
        CEP.TRC(SCCGWA, DDCSSTSW.STS);
        CEP.TRC(SCCGWA, DDCSSTSW.EFF_DATE);
        CEP.TRC(SCCGWA, DDCSSTSW.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSSTSW.REASON);
        S000_CALL_DDZSSTSW();
        if (pgmRtn) return;
    }
    public void R000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZSSTSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MOD-STSW", DDCSSTSW);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
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
