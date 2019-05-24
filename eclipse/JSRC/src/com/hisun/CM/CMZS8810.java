package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS8810 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM110";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    String WS_CI_NO = " ";
    String WS_CUS_NAME = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    char WS_STS = ' ';
    CMZS8810_WS_BATH_PARM WS_BATH_PARM = new CMZS8810_WS_BATH_PARM();
    char WS_END_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICOPDCP CICOPDCP = new CICOPDCP();
    DCCSSPOT DCCSSPOT = new DCCSSPOT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    CMCS8810 CMCS8810;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCSBSPO CMCSBSPO;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA, CMCS8810 CMCS8810) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS8810 = CMCS8810;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS8810 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, "222");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "333");
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        CEP.TRC(SCCGWA, "444");
        CEP.TRC(SCCGWA, "666");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INQ_INF();
        if (pgmRtn) return;
        B020_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_INQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMCS8810.CUS_AC;
        CEP.TRC(SCCGWA, CMCS8810.CUS_AC);
        R000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        WS_CUS_NAME = CICCUST.O_DATA.O_CI_NM;
        WS_TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS8810.CUS_AC;
        R000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_STS = CICQACRI.O_DATA.O_STS;
    }
    public void B020_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CMCS8810.CHK_RES = "0000000000";
        if (CMCS8810.ID_TYP.trim().length() > 0 
            && !CMCS8810.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
            if (CMCS8810.CHK_RES == null) CMCS8810.CHK_RES = "";
            JIBS_tmp_int = CMCS8810.CHK_RES.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8810.CHK_RES += " ";
            CMCS8810.CHK_RES = CMCS8810.CHK_RES.substring(0, 4 - 1) + "1" + CMCS8810.CHK_RES.substring(4 + 1 - 1);
        }
        if (CMCS8810.ID_NO.trim().length() > 0 
            && !CMCS8810.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
            if (CMCS8810.CHK_RES == null) CMCS8810.CHK_RES = "";
            JIBS_tmp_int = CMCS8810.CHK_RES.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8810.CHK_RES += " ";
            CMCS8810.CHK_RES = CMCS8810.CHK_RES.substring(0, 4 - 1) + "1" + CMCS8810.CHK_RES.substring(4 + 1 - 1);
        }
        if (CMCS8810.CUS_NAME.trim().length() > 0 
            && !CMCS8810.CUS_NAME.equalsIgnoreCase(WS_CUS_NAME)) {
            if (CMCS8810.CHK_RES == null) CMCS8810.CHK_RES = "";
            JIBS_tmp_int = CMCS8810.CHK_RES.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8810.CHK_RES += " ";
            CMCS8810.CHK_RES = CMCS8810.CHK_RES.substring(0, 3 - 1) + "1" + CMCS8810.CHK_RES.substring(3 + 1 - 1);
        }
        if (CMCS8810.TEL_NO.trim().length() > 0 
            && !CMCS8810.TEL_NO.equalsIgnoreCase(WS_TEL_NO)) {
            if (CMCS8810.CHK_RES == null) CMCS8810.CHK_RES = "";
            JIBS_tmp_int = CMCS8810.CHK_RES.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8810.CHK_RES += " ";
            CMCS8810.CHK_RES = CMCS8810.CHK_RES.substring(0, 5 - 1) + "1" + CMCS8810.CHK_RES.substring(5 + 1 - 1);
        }
        if (CMCS8810.CI_NO.trim().length() > 0 
            && !CMCS8810.CI_NO.equalsIgnoreCase(WS_CI_NO)) {
            if (CMCS8810.CHK_RES == null) CMCS8810.CHK_RES = "";
            JIBS_tmp_int = CMCS8810.CHK_RES.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8810.CHK_RES += " ";
            CMCS8810.CHK_RES = CMCS8810.CHK_RES.substring(0, 2 - 1) + "1" + CMCS8810.CHK_RES.substring(2 + 1 - 1);
        }
        if (WS_STS != '0') {
            if (CMCS8810.CHK_RES == null) CMCS8810.CHK_RES = "";
            JIBS_tmp_int = CMCS8810.CHK_RES.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8810.CHK_RES += " ";
            CMCS8810.CHK_RES = "1" + CMCS8810.CHK_RES.substring(1);
        }
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CMCS8810.RET_ID_TYP = WS_ID_TYP;
        CMCS8810.RET_ID_NO = WS_ID_NO;
        CMCS8810.RET_CUS_NAME = WS_CUS_NAME;
        CMCS8810.RET_TEL_NO = WS_TEL_NO;
        CMCS8810.RET_CI_NO = WS_CI_NO;
        SCCFMT.DATA_PTR = CMCS8810;
        SCCFMT.DATA_LEN = 1123;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
    }
    public void R000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
