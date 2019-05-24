package com.hisun.PS;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZTSWHP {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm PSTEINF_RD;
    DBParm PSTPBIN_RD;
    String K_EXG_BK_NO = "001";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String K_CNTR_TYPE = "CACH";
    String K_CNTR_TYPE1 = "BADR";
    String K_CNTR_TYPE2 = "LOCL";
    String K_EVENT_CODE = "CR";
    String K_EVENT_CODE1 = "DR";
    String K_EVENT_CODE2 = "NETCL";
    String K_PROD_CO1 = "2304020303";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_NUM = 0;
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    BPCOCKEM BPCOCKEM = new BPCOCKEM();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCTSWHP PSCTSWHP;
    public void MP(SCCGWA SCCGWA, PSCTSWHP PSCTSWHP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCTSWHP = PSCTSWHP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZTSWHP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_CHECK_TRAN_DATA();
        B300_WRITE_PSREINF();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCTSWHP.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        if (PSCTSWHP.EXG_DT != PSCTSWHP.NEXT_EXG_DT) {
            IBS.init(SCCGWA, BPCPCMWD);
            BPCPCMWD.CALCD[1-1] = "CN";
            BPCPCMWD.DATE_TYPE = 'B';
            BPCPCMWD.CHECK_DATE = PSCTSWHP.NEXT_EXG_DT;
            S000_CALL_BPZPCMWD();
            CEP.TRC(SCCGWA, BPCPCMWD.HOLIDAY_FLG_ALL);
            if (BPCPCMWD.HOLIDAY_FLG_ALL == 'Y') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PP_WK_DT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, PSREINF);
        CEP.TRC(SCCGWA, PSCTSWHP.BK_NO);
        CEP.TRC(SCCGWA, PSCTSWHP.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCTSWHP.EXG_CCY);
        PSREINF.KEY.EXG_BK_NO = PSCTSWHP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCTSWHP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCTSWHP.EXG_CCY;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_READ_PSTEINF();
        CEP.TRC(SCCGWA, PSREINF.EXG_NO);
        CEP.TRC(SCCGWA, PSREINF.EXG_YEND_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_JOIN_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_YEND_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_SYS_STS);
        if (PSREINF.EXG_SYS_STS.equalsIgnoreCase("03") 
            || PSREINF.EXG_SYS_STS.equalsIgnoreCase("04") 
            || PSREINF.EXG_SYS_STS.equalsIgnoreCase("3") 
            || PSREINF.EXG_SYS_STS.equalsIgnoreCase("4") 
            || PSREINF.EXG_SYS_STS.equalsIgnoreCase("01")) {
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ENIF_STS_QS_MST_ERR;
            S000_ERR_MSG_PROC();
        }
        if (!PSCTSWHP.OUR_EXG_NO.equalsIgnoreCase(PSREINF.EXG_NO)) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_ERR;
            S000_ERR_MSG_PROC();
        }
        JIBS_tmp_str[0] = "" + PSCTSWHP.NEXT_EXG_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")) {
            IBS.init(SCCGWA, BPCOCKEM);
            BPCOCKEM.CAL_CODE = "CN";
            JIBS_tmp_str[0] = "" + PSCTSWHP.NEXT_EXG_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) BPCOCKEM.YEAR = 0;
            else BPCOCKEM.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
            JIBS_tmp_str[0] = "" + PSCTSWHP.NEXT_EXG_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_NUM = 0;
            else WS_NUM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
            CEP.TRC(SCCGWA, WS_NUM);
            S000_CALL_BPZOCKEM();
            CEP.TRC(SCCGWA, BPCOCKEM.EOM_DATE[WS_NUM-1].EOM_DAY);
            JIBS_tmp_str[0] = "" + PSCTSWHP.NEXT_EXG_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
            CEP.TRC(SCCGWA, 222);
            JIBS_tmp_str[0] = "" + PSCTSWHP.NEXT_EXG_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (BPCOCKEM.EOM_DATE[WS_NUM-1].EOM_DAY != Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1))) {
                if (PSCTSWHP.NEXT_EXG_TMS > PSREINF.EXG_JOIN_TMS) {
                    WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_OVR_MAX;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (PSCTSWHP.NEXT_EXG_TMS > PSREINF.EXG_JOIN_TMS + PSREINF.EXG_YEND_TMS) {
                    WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_OVR_MAX;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, 11111111);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_M_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, 3333);
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = PSCTSWHP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCTSWHP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCTSWHP.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCTSWHP.OUR_EXG_NO;
        CEP.TRC(SCCGWA, 3333);
        T100_READ_PSTPBIN();
        CEP.TRC(SCCGWA, PSRPBIN.EXG_NB);
        CEP.TRC(SCCGWA, PSREINF.EXG_JOIN_TMS);
        CEP.TRC(SCCGWA, 4444);
        CEP.TRC(SCCGWA, PSREINF.EXG_JOIN_TMS);
        CEP.TRC(SCCGWA, PSRPBIN.EXG_NB);
        if (PSREINF.EXG_JOIN_TMS > PSRPBIN.EXG_NB) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TMS_TBA_MST_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_WRITE_PSREINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCTSWHP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCTSWHP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCTSWHP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.upd = true;
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, PSREINF.EXG_SYS_STS);
        PSREINF.EXG_PRE_DT = PSREINF.EXG_DT;
        PSREINF.EXG_PRE_TMS = PSREINF.EXG_TMS;
        PSREINF.EXG_DT = PSCTSWHP.NEXT_EXG_DT;
        PSREINF.EXG_TMS = PSCTSWHP.NEXT_EXG_TMS;
        PSREINF.EXG_SYS_STS = "01";
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.REWRITE(SCCGWA, PSREINF, PSTEINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EINF_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTEINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void T000_READ_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EINF_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTEINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
    }
    public void S000_CALL_BPZOCKEM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-END-MON", BPCOCKEM);
        if (BPCOCKEM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCKEM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T100_READ_PSTPBIN() throws IOException,SQLException,Exception {
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        IBS.READ(SCCGWA, PSRPBIN, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PBKA_NOT_FIND;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTPBIN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
