package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6300 {
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
    DDCSADIF DDCSADIF = new DDCSADIF();
    CICQACAC CICQACAC = new CICQACAC();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINTB DDRINTB = new DDRINTB();
    DDRADMN DDRADMN = new DDRADMN();
    SCCGWA SCCGWA;
    DDB6300_AWA_6300 DDB6300_AWA_6300;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT6300 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6300_AWA_6300>");
        DDB6300_AWA_6300 = (DDB6300_AWA_6300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B100_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.FUNC);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.ADP_TYPE);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.AC);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.CCY);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.CCY_TYP);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.STR_DATE);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.EXP_DATE);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.PAGE_NUM);
        CEP.TRC(SCCGWA, DDB6300_AWA_6300.PAGE_ROW);
        if (DDB6300_AWA_6300.FUNC == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT);
        }
        if (DDB6300_AWA_6300.FUNC != '1' 
            && DDB6300_AWA_6300.FUNC != '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_INVALID);
        }
        if (DDB6300_AWA_6300.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDB6300_AWA_6300.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDB6300_AWA_6300.CCY_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT);
        }
        if (DDB6300_AWA_6300.FUNC == '2') {
            if (DDB6300_AWA_6300.ADP_TYPE.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADP_TYPE_M_INP);
            }
            if (DDB6300_AWA_6300.STR_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDT_M_INPUT);
            }
            if (DDB6300_AWA_6300.EXP_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_INPUT);
            } else {
                if (DDB6300_AWA_6300.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_GTEQ_ACDT);
                }
                if (DDB6300_AWA_6300.EXP_DATE <= DDB6300_AWA_6300.STR_DATE) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_GT_STRDT);
                }
            }
        }
    }
    public void B100_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSADIF);
        DDCSADIF.FUNC = DDB6300_AWA_6300.FUNC;
        DDCSADIF.ADP_TYPE = DDB6300_AWA_6300.ADP_TYPE;
        DDCSADIF.AC = DDB6300_AWA_6300.AC;
        DDCSADIF.CCY = DDB6300_AWA_6300.CCY;
        DDCSADIF.CCY_TYP = DDB6300_AWA_6300.CCY_TYP;
        DDCSADIF.STR_DATE = DDB6300_AWA_6300.STR_DATE;
        DDCSADIF.EXP_DATE = DDB6300_AWA_6300.EXP_DATE;
        DDCSADIF.PAGE_NUM = DDB6300_AWA_6300.PAGE_NUM;
        DDCSADIF.PAGE_ROW = DDB6300_AWA_6300.PAGE_ROW;
        CEP.TRC(SCCGWA, DDCSADIF.FUNC);
        CEP.TRC(SCCGWA, DDCSADIF.ADP_TYPE);
        CEP.TRC(SCCGWA, DDCSADIF.AC);
        CEP.TRC(SCCGWA, DDCSADIF.CCY);
        CEP.TRC(SCCGWA, DDCSADIF.CCY_TYP);
        CEP.TRC(SCCGWA, DDCSADIF.STR_DATE);
        CEP.TRC(SCCGWA, DDCSADIF.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSADIF.PAGE_NUM);
        CEP.TRC(SCCGWA, DDCSADIF.PAGE_ROW);
        S000_CALL_DDZSADIF();
        if (pgmRtn) return;
    }
    public void R000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_DDZSADIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MOD-ADIF", DDCSADIF);
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
