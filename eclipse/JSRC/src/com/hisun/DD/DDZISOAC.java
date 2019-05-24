package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZISOAC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    brParm DDTMST_BR = new brParm();
    boolean pgmRtn = false;
    String WS_AC = " ";
    String WS_REL_AC = " ";
    char WS_TABLE_REC_FLG = ' ';
    char WS_CCY_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    CICCUST CICCUST = new CICCUST();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCOSOAC DDCOSOAC = new DDCOSOAC();
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_OPEN_BR = 0;
    SCCGWA SCCGWA;
    DDCISOAC DDCISOAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCISOAC DDCISOAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCISOAC = DDCISOAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZISOAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_MPAG_INIT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCISOAC.CI_NO);
        if (DDCISOAC.CI_NO.trim().length() > 0) {
            B020_INQ_BY_CINO();
            if (pgmRtn) return;
        } else {
            B030_INQ_BY_DATE();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCISOAC.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, DDCISOAC.STR_DATE);
            CEP.TRC(SCCGWA, DDCISOAC.END_DATE);
            if (DDCISOAC.STR_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STR_DATE_M_INPUT);
            }
            if (DDCISOAC.END_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_END_DATE_M_INPUT);
            }
        }
        WS_STR_DATE = DDCISOAC.STR_DATE;
        WS_END_DATE = DDCISOAC.END_DATE;
        WS_OPEN_BR = DDCISOAC.OPEN_BR;
    }
    public void B020_INQ_BY_CINO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '1';
        CICQCIAC.DATA.CI_NO = DDCISOAC.CI_NO;
        CICQCIAC.DATA.CNTRCT_TYP = "103";
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO;
        WS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        B020_01_GET_ACO_INFO();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0) {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1));
            CEP.TRC(SCCGWA, DDCISOAC.RECV_FLG);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if ((DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && DDCISOAC.RECV_FLG == '0') 
                || (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                && DDCISOAC.RECV_FLG == '1') 
                || DDCISOAC.RECV_FLG == ' ') {
                CEP.TRC(SCCGWA, "TTTTT");
                R000_TRANS_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_01_GET_ACO_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = WS_AC;
        CICQACRL.DATA.AC_REL = "09";
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC);
        if (CICQACRL.RC.RC_CODE == 8054) {
            WS_REL_AC = CICQACRL.DATA.AC_NO;
        } else if (CICQACRL.RC.RC_CODE == 0) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            CEP.ERR(SCCGWA, CICQACRL.RC);
        }
        CEP.TRC(SCCGWA, WS_REL_AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.CCY_ACAC = "156";
        CICQACAC.DATA.CR_FLG = '1';
        CICQACAC.DATA.AGR_NO = WS_REL_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
    }
    public void B030_INQ_BY_DATE() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTMST();
        if (pgmRtn) return;
        T000_READNEXT_DDTMST();
        if (pgmRtn) return;
        if (WS_TABLE_REC_FLG == 'N') {
            Z_RET();
            if (pgmRtn) return;
        }
        while (WS_TABLE_REC_FLG != 'N') {
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = DDRMST.KEY.CUS_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("103") 
                && CICQACRI.O_DATA.O_STS != '1') {
                WS_AC = DDRMST.KEY.CUS_AC;
                B020_01_GET_ACO_INFO();
                if (pgmRtn) return;
                if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0 
                    && DDRCCY.STS != 'C') {
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1));
                    CEP.TRC(SCCGWA, DDCISOAC.RECV_FLG);
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    if ((DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                        && DDCISOAC.RECV_FLG == '0') 
                        || (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                        && DDCISOAC.RECV_FLG == '1') 
                        || DDCISOAC.RECV_FLG == ' ') {
                        R000_TRANS_OUTPUT_DATA();
                        if (pgmRtn) return;
                    }
                }
            }
            T000_READNEXT_DDTMST();
            if (pgmRtn) return;
        }
    }
    public void R000_MPAG_INIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 428;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOSOAC);
        DDCOSOAC.AC = DDRMST.KEY.CUS_AC;
        DDCOSOAC.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        DDCOSOAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        DDCOSOAC.AC_NAME = CICCUST.O_DATA.O_CI_NM;
        DDCOSOAC.AC_STS = DDRMST.AC_STS;
        DDCOSOAC.OPEN_BR = DDRMST.OPEN_DP;
        DDCOSOAC.OPEN_DATE = DDRMST.OPEN_DATE;
        DDCOSOAC.RECEIVE_FLG = '0';
        DDCOSOAC.AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
        CEP.TRC(SCCGWA, DDCOSOAC.AC);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDCOSOAC.AVL_BAL);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            DDCOSOAC.CARD_NO = DCCUCINF.CARD_NO;
            DDCOSOAC.CARD_STS = DCCUCINF.CARD_STS;
            DDCOSOAC.RECV_BR = DCCUCINF.ISSU_BR;
            DDCOSOAC.RECV_DATE = DCCUCINF.ISSU_DT;
            DDCOSOAC.RECEIVE_FLG = '1';
        }
        S000_WRITE_QUEUE();
        if (pgmRtn) return;
    }
    public void S000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, "MPAG IN");
        SCCMPAG.FUNC = 'D';
        SCCMPAG.MAX_COL_NO = 428;
        SCCMPAG.DATA_LEN = 428;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOSOAC);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.eqWhere = "CUS_AC";
        DDTMST_RD.where = "( ( :WS_STR_DATE = 0 "
            + "AND :WS_END_DATE = 0 ) "
            + "OR ( OPEN_DATE >= :WS_STR_DATE "
            + "AND OPEN_DATE <= :WS_END_DATE ) ) "
            + "AND ( :WS_OPEN_BR = 0 "
            + "OR OPEN_DP = :WS_OPEN_BR ) "
            + "AND AC_STS < > 'C'";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_REC_FLG = 'Y';
        }
    }
    public void T000_STARTBR_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_BR.rp = new DBParm();
        DDTMST_BR.rp.TableName = "DDTMST";
        DDTMST_BR.rp.where = "OPEN_DATE >= :WS_STR_DATE "
            + "AND OPEN_DATE <= :WS_END_DATE "
            + "AND ( :WS_OPEN_BR = 0 "
            + "OR OPEN_DP = :WS_OPEN_BR ) "
            + "AND AC_STS < > 'C' "
            + "AND YW_TYP = '103'";
        IBS.STARTBR(SCCGWA, DDRMST, this, DDTMST_BR);
    }
    public void T000_READNEXT_DDTMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRMST, this, DDTMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
