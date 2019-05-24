package com.hisun.TD;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class TDZLMBP {
    int JIBS_tmp_int;
    brParm TDTLMT_BR = new brParm();
    brParm TDTOTHE_BR = new brParm();
    DBParm TDTOTHE_RD;
    boolean pgmRtn = false;
    int K_HEAD_BR = 043999;
    int K_MAX_CNT = 2000;
    String WS_MSGID = " ";
    short WS_DATA_LEN = 0;
    short WS_CNT = 0;
    short WS_LM_POINT_C = 0;
    short WS_TIME = 0;
    short WS_TIME1 = 0;
    short WS_TIME2 = 0;
    int WS_DATE_1 = 0;
    int WS_DATE_2 = 0;
    int WS_BP_BR1 = 0;
    int WS_BP_BR2 = 0;
    char WS_X_FLG = ' ';
    char WS_LMT_FLG = ' ';
    char WS_OTHE_FLG = ' ';
    TDZLMBP_WS_CFLG[] WS_CFLG = new TDZLMBP_WS_CFLG[500];
    int WS_TST_BR = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCBPCU TDCBPCU = new TDCBPCU();
    TDROTHE TDROTHE = new TDROTHE();
    TDRLMT TDRLMT = new TDRLMT();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPRPORGM BPRPORGM = new BPRPORGM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    TDCLMBP TDCLMBP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public TDZLMBP() {
        for (int i=0;i<500;i++) WS_CFLG[i] = new TDZLMBP_WS_CFLG();
    }
    public void MP(SCCGWA SCCGWA, TDCLMBP TDCLMBP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCLMBP = TDCLMBP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZLMBP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, TDCBPCU);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCLMBP.ACTI_NO);
        if (TDCLMBP.ACTI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NO_IN_ERR);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B050_BROWSE_PROCESS();
        if (pgmRtn) return;
        B101_ACTI_NEW();
        if (pgmRtn) return;
        B102_ACTI_OLD();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPRORGM);
        IBS.init(SCCGWA, BPRPARM);
        BPCRORGM.INFO.FUNC = 'S';
        BPCRORGM.INFO.POINTER = BPRORGM;
        BPCRORGM.INFO.LEN = 1198;
        S000_CALL_BPZRORGM_B();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AFTER STARTBR");
        if (BPCRORGM.RETURN_INFO == 'N') {
            WS_MSGID = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_CNT = 1; BPCRORGM.RETURN_INFO != 'N' 
            && WS_CNT < K_MAX_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, "ENTER PERFORM");
            CEP.TRC(SCCGWA, BPCRORGM.RETURN_INFO);
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1198;
            S000_CALL_BPZRORGM_B();
            if (pgmRtn) return;
            if (BPCRORGM.RETURN_INFO == 'F' 
                && BPRPORGM.DATA_TXT.TYPE.equalsIgnoreCase("12")) {
                CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
                CEP.TRC(SCCGWA, BPRPORGM.DESC);
                CEP.TRC(SCCGWA, BPRPORGM.CDESC);
                WS_TIME += 1;
                TDCBPCU.BR_NO[WS_TIME-1].BR_CU = BPRPORGM.KEY.BR;
                WS_TST_BR = BPRPORGM.KEY.BR;
                CEP.TRC(SCCGWA, WS_TIME);
                CEP.TRC(SCCGWA, WS_TST_BR);
            }
        }
        CEP.TRC(SCCGWA, WS_TIME);
        BPCRORGM.RETURN_INFO = ' ';
        BPCRORGM.INFO.FUNC = 'E';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1198;
        S000_CALL_BPZRORGM_B();
        if (pgmRtn) return;
    }
    public void B101_ACTI_NEW() throws IOException,SQLException,Exception {
        WS_TIME1 = 1;
        CEP.TRC(SCCGWA, WS_TIME1);
        while (TDCBPCU.BR_NO[WS_TIME1-1].BR_CU != 0) {
            B500_JUDGE_MODIFY();
            if (pgmRtn) return;
            WS_TIME1 += 1;
        }
    }
    public void B102_ACTI_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROTHE);
        WS_TIME1 = 0;
        WS_TIME2 = 0;
        TDROTHE.KEY.ACTI_NO = TDCLMBP.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        T000_STARTBR_TDTOTHE();
        if (pgmRtn) return;
        T000_READNEXT_TDTOTHE();
        if (pgmRtn) return;
        while (WS_OTHE_FLG != 'N') {
            WS_TIME2 += 1;
            CEP.TRC(SCCGWA, WS_TIME2);
            WS_TIME1 = 1;
            while (TDCBPCU.BR_NO[WS_TIME1-1].BR_CU != 0) {
                TDCBPCU.CFLG[WS_TIME1-1].FLG = ' ';
                B600_JUDGE_MODIFY();
                if (pgmRtn) return;
                WS_TIME1 += 1;
            }
            T000_READNEXT_TDTOTHE();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTOTHE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.KEY.ACTI_NO = TDCLMBP.ACTI_NO;
        T000_READUP_TDTOTHE();
        if (pgmRtn) return;
        TDROTHE.SUC_FLG = '0';
        TDROTHE.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDROTHE.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTOTHE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TIME2);
    }
    public void B500_JUDGE_MODIFY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        WS_X_FLG = ' ';
        WS_LMT_FLG = ' ';
        WS_BP_BR1 = 0;
        WS_BP_BR2 = 0;
        IBS.init(SCCGWA, BPCPORUP);
        BPCPORUP.DATA_INFO.BR = TDCBPCU.BR_NO[WS_TIME1-1].BR_CU;
        CEP.TRC(SCCGWA, WS_TIME1);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        S000_CALL_BPZPORUP();
        if (pgmRtn) return;
        WS_BP_BR1 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        CEP.TRC(SCCGWA, WS_BP_BR1);
        if (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != K_HEAD_BR) {
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = WS_BP_BR1;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            WS_BP_BR2 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        } else {
            WS_BP_BR2 = K_HEAD_BR;
        }
        CEP.TRC(SCCGWA, WS_BP_BR2);
        CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
        CEP.TRC(SCCGWA, TDROTHE.END_DATE);
        WS_DATE_1 = TDROTHE.STR_DATE;
        WS_DATE_2 = TDROTHE.END_DATE;
        TDRLMT.KEY.ACTI_NO = TDCLMBP.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCLMBP.PROD_CD;
        TDRLMT.KEY.BR = TDCBPCU.BR_NO[WS_TIME1-1].BR_CU;
        T000_BP_STARTBR_TDTLMT();
        if (pgmRtn) return;
        T000_BP_RENEXT_TDTLMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "***************");
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, TDRLMT.UNAGN_USE_BAL);
        CEP.TRC(SCCGWA, TDRLMT.UNAGN_CURR_BAL);
        if (WS_LMT_FLG == 'Y') {
            if (TDRLMT.UNAGN_BAL > 0) {
                if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                JIBS_tmp_int = TDRLMT.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() > 0) {
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_LM_POINT_C = 0;
                    else WS_LM_POINT_C = Short.parseShort(TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1));
                } else {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_ERR_GAME_OVER);
                }
                if (WS_LM_POINT_C > 0) {
                    WS_CFLG[WS_TIME1-1].WS_FLG = '0';
                } else {
                    WS_CFLG[WS_TIME1-1].WS_FLG = '1';
                }
            } else {
                if (TDRLMT.SHAR_FLG == '1') {
                    T000_BP_RENEXT_TDTLMT();
                    if (pgmRtn) return;
                    if (TDRLMT.UNAGN_BAL > 0) {
                        WS_CFLG[WS_TIME1-1].WS_FLG = '1';
                    } else {
                        WS_CFLG[WS_TIME1-1].WS_FLG = '0';
                    }
                } else {
                    WS_CFLG[WS_TIME1-1].WS_FLG = '0';
                }
            }
        }
        T000_ENDBR_TDTLMT();
        if (pgmRtn) return;
    }
    public void B600_JUDGE_MODIFY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        WS_X_FLG = ' ';
        WS_LMT_FLG = ' ';
        WS_BP_BR1 = 0;
        WS_BP_BR2 = 0;
        IBS.init(SCCGWA, BPCPORUP);
        BPCPORUP.DATA_INFO.BR = TDCBPCU.BR_NO[WS_TIME1-1].BR_CU;
        S000_CALL_BPZPORUP();
        if (pgmRtn) return;
        WS_BP_BR1 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        CEP.TRC(SCCGWA, WS_BP_BR1);
        if (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != K_HEAD_BR) {
            IBS.init(SCCGWA, BPCPORUP);
            BPCPORUP.DATA_INFO.BR = WS_BP_BR1;
            S000_CALL_BPZPORUP();
            if (pgmRtn) return;
            WS_BP_BR2 = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
        } else {
            WS_BP_BR2 = K_HEAD_BR;
        }
        CEP.TRC(SCCGWA, WS_BP_BR2);
        TDRLMT.KEY.ACTI_NO = TDROTHE.KEY.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDROTHE.PROD_CD;
        TDRLMT.KEY.BR = TDCBPCU.BR_NO[WS_TIME1-1].BR_CU;
        T000_BP_STARTBR_TDTLMT();
        if (pgmRtn) return;
        T000_BP_RENEXT_TDTLMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "***************");
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, TDRLMT.UNAGN_BAL);
        if (WS_LMT_FLG == 'Y') {
            if (TDRLMT.UNAGN_BAL > 0) {
                if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                JIBS_tmp_int = TDRLMT.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() > 0) {
                    if (TDRLMT.LM_POINT == null) TDRLMT.LM_POINT = "";
                    JIBS_tmp_int = TDRLMT.LM_POINT.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDRLMT.LM_POINT += " ";
                    if (TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_LM_POINT_C = 0;
                    else WS_LM_POINT_C = Short.parseShort(TDRLMT.LM_POINT.substring(7 - 1, 7 + 2 - 1));
                } else {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_ERR_GAME_OVER);
                }
                if (WS_LM_POINT_C > 0) {
                    TDCBPCU.CFLG[WS_TIME1-1].FLG = '0';
                } else {
                    TDCBPCU.CFLG[WS_TIME1-1].FLG = '1';
                }
            } else {
                if (TDRLMT.SHAR_FLG == '1') {
                    T000_BP_RENEXT_TDTLMT();
                    if (pgmRtn) return;
                    if (TDRLMT.UNAGN_BAL > 0) {
                        TDCBPCU.CFLG[WS_TIME1-1].FLG = '1';
                    } else {
                        TDCBPCU.CFLG[WS_TIME1-1].FLG = '0';
                    }
                } else {
                    TDCBPCU.CFLG[WS_TIME1-1].FLG = '0';
                }
            }
            CEP.TRC(SCCGWA, WS_TIME1);
            CEP.TRC(SCCGWA, TDCBPCU.CFLG[WS_TIME1-1].FLG);
            CEP.TRC(SCCGWA, WS_CFLG[WS_TIME1-1].WS_FLG);
            if (TDCBPCU.CFLG[WS_TIME1-1].FLG == '1' 
                && WS_CFLG[WS_TIME1-1].WS_FLG == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CANOT_HD);
            }
        }
    }
    public void T000_BP_STARTBR_TDTLMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        TDTLMT_BR.rp = new DBParm();
        TDTLMT_BR.rp.TableName = "TDTLMT";
        TDTLMT_BR.rp.where = "PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND ( BR = :TDRLMT.KEY.BR "
            + "OR BR = :WS_BP_BR1 "
            + "OR BR = :WS_BP_BR2 )";
        TDTLMT_BR.rp.upd = true;
        TDTLMT_BR.rp.order = "LM_CNT DESC";
        IBS.STARTBR(SCCGWA, TDRLMT, this, TDTLMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND);
        }
    }
    public void T000_BP_RENEXT_TDTLMT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRLMT, this, TDTLMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "########");
            CEP.TRC(SCCGWA, WS_X_FLG);
            WS_X_FLG = 'Y';
            WS_LMT_FLG = 'Y';
        }
    }
    public void T000_ENDBR_TDTLMT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTLMT_BR);
    }
    public void T000_STARTBR_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_BR.rp = new DBParm();
        TDTOTHE_BR.rp.TableName = "TDTOTHE";
        TDTOTHE_BR.rp.where = "PROD_CD = :TDROTHE.PROD_CD "
            + "AND CCY = :TDROTHE.CCY "
            + "AND TERM = :TDROTHE.TERM "
            + "AND ACTI_NO < > :TDROTHE.KEY.ACTI_NO "
            + "AND ACTI_FLG = '0' "
            + "AND SUC_FLG < > '1' "
            + "AND ( ( STR_DATE <= :TDROTHE.STR_DATE "
            + "AND END_DATE > :TDROTHE.STR_DATE ) "
            + "OR ( STR_DATE < :TDROTHE.END_DATE "
            + "AND END_DATE >= :TDROTHE.END_DATE ) "
            + "OR ( STR_DATE >= :TDROTHE.STR_DATE "
            + "AND END_DATE <= :TDROTHE.END_DATE ) )";
        TDTOTHE_BR.rp.order = "ACTI_NO";
        IBS.STARTBR(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
    }
    public void T000_READNEXT_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_OTHE_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTOTHE_BR);
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
    }
    public void T000_READUP_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.upd = true;
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
    }
    public void T000_REWRITE_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.REWRITE(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
    }
    public void S000_CALL_BPZRORGM_B() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-ORGM", BPCRORGM);
        CEP.TRC(SCCGWA, BPCRORGM.RETURN_INFO);
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
