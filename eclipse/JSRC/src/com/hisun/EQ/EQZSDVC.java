package com.hisun.EQ;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPQPRD;
import com.hisun.CI.CICACCU;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWRMSG;

public class EQZSDVC {
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    String K_BSZ_BANKID = "01";
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_1 = "EQ803";
    String K_OUTPUT_FMT_9 = "EQ205";
    double K_TAX_RAT = 20;
    String K_CCY_CNY = "156";
    String K_HIS_FMT = "EQCDVC";
    String K_HIS_RMK = "EQ. BONUS/SEND STOCK";
    String K_BRAC_FH1 = "70666060015620190202000001";
    String K_BRAC_FH2 = "70667050015620190202000001";
    String K_BRAC_FH3 = "70667150015620190202000001";
    String K_BRAC_FH4 = "70667250015620190202000001";
    String K_BRAC_FH5 = "70667350015620190202000001";
    int K_DIV_BR1 = 706660600;
    int K_DIV_BR2 = 706670500;
    int K_DIV_BR3 = 706671500;
    int K_DIV_BR4 = 706672500;
    int K_DIV_BR5 = 706673500;
    String WS_MSGID = " ";
    String WS_TXN_PRODCD = " ";
    double WS_TXN_DICP_PCT = 0;
    double WS_TXN_DICP_AMT = 0;
    double WS_TXN_TAX = 0;
    double WS_TXN_TAX1 = 0;
    double WS_TXN_TAX2 = 0;
    double WS_TXN_DIVAMT = 0;
    double WS_DICP_PCT = 0;
    double WS_EQ_QTY = 0;
    double WS_FRZ_QTY = 0;
    double WS_TMP_QTY = 0;
    double WS_TMP_QTY2 = 0;
    double WS_DC_CAL_AMQT = 0;
    double WS_DC_CAL_AMQT1 = 0;
    double WS_DC_CAL_AMQT2 = 0;
    double WS_DIV_AMT = 0;
    char WS_CPN_TYP = ' ';
    String WS_BR_AC = " ";
    EQZSDVC_WS_OUT_DATA1 WS_OUT_DATA1 = new EQZSDVC_WS_OUT_DATA1();
    EQZSDVC_WS_OUT_DATA2 WS_OUT_DATA2 = new EQZSDVC_WS_OUT_DATA2();
    double WS_AMT1 = 0;
    String WS_CHAR = " ";
    long WS_AMT2 = 0;
    int WS_UPT_NO = 0;
    char WS_CHANGE_FLG = ' ';
    int WS_DIV_BR = 0;
    char WS_DVC_FLG = ' ';
    char WS_DVCD_FLG = ' ';
    char WS_DVCF_FLG = ' ';
    char WS_ACT_FLG = ' ';
    char WS_ACTD_FLG = ' ';
    char WS_BVT_FLG = ' ';
    char WS_FRZ_FLG = ' ';
    char WS_EOF_DVC_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_CI_SAME_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_NORMAL_STS = 'N';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRDVC EQRDVC = new EQRDVC();
    EQRDVCD EQRDVCD = new EQRDVCD();
    EQRDVCF EQRDVCF = new EQRDVCF();
    EQRACT EQRACT = new EQRACT();
    EQRACTD EQRACTD = new EQRACTD();
    EQRBVT EQRBVT = new EQRBVT();
    EQRWDZ EQRWDZ = new EQRWDZ();
    EQRFRZ EQRFRZ = new EQRFRZ();
    EQCRDVC EQCRDVC = new EQCRDVC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    EQCOQ200_OPT_8200 EQCOQ200_OPT_8200 = new EQCOQ200_OPT_8200();
    EQCOQ205_OPT_8205 EQCOQ205_OPT_8205 = new EQCOQ205_OPT_8205();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQCSDVC EQCSDVC;
    public void MP(SCCGWA SCCGWA, EQCSDVC EQCSDVC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCSDVC = EQCSDVC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZSDVC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCSDVC.FUNC);
        if (EQCSDVC.FUNC == 'B') {
            B010_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (EQCSDVC.FUNC == 'A') {
            B020_ADD_APPLY_PROC();
            if (pgmRtn) return;
        } else if (EQCSDVC.FUNC == 'U') {
            B030_APPLY_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (EQCSDVC.FUNC == 'D') {
            B040_APPLY_DELETE_PROC();
            if (pgmRtn) return;
        } else if (EQCSDVC.FUNC == 'I') {
            B050_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (EQCSDVC.FUNC == 'C') {
            B060_BONUS_PROC();
            if (pgmRtn) return;
        } else if (EQCSDVC.FUNC == 'E') {
            B070_SENDST_PROC();
            if (pgmRtn) return;
        } else if (EQCSDVC.FUNC == 'F') {
            B080_CHECK_PROC();
            if (pgmRtn) return;
        } else {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_INVALID_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRDVC);
        IBS.init(SCCGWA, EQCRDVC);
        CEP.TRC(SCCGWA, EQCSDVC.SUB_FUNC);
        EQRDVC.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQCRDVC.FUNC = 'B';
        if (EQCSDVC.SUB_FUNC == 'T') {
            EQRDVC.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
            EQCRDVC.OPT = 'D';
        } else if (EQCSDVC.SUB_FUNC == 'F') {
            EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
            EQCRDVC.OPT = 'F';
        } else if (EQCSDVC.SUB_FUNC == 'S') {
            EQRDVC.REC_STS = EQCSDVC.DATA.REC_STS;
            EQCRDVC.OPT = 'T';
        } else if (EQCSDVC.SUB_FUNC == 'D') {
            EQRDVC.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
            EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
            EQCRDVC.OPT = 'B';
        } else if (EQCSDVC.SUB_FUNC == 'U') {
            EQRDVC.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
            EQRDVC.REC_STS = EQCSDVC.DATA.REC_STS;
            EQCRDVC.OPT = 'C';
        } else if (EQCSDVC.SUB_FUNC == 'G') {
            EQRDVC.REC_STS = EQCSDVC.DATA.REC_STS;
            EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
            EQCRDVC.OPT = 'G';
        } else if (EQCSDVC.SUB_FUNC == 'L') {
            EQRDVC.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
            EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
            EQRDVC.REC_STS = EQCSDVC.DATA.REC_STS;
            EQCRDVC.OPT = 'L';
        } else if (EQCSDVC.SUB_FUNC == 'A') {
            EQCRDVC.OPT = 'S';
        } else {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_INVALID_SUB_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_EQZRDVC();
        if (pgmRtn) return;
        EQCRDVC.FUNC = 'B';
        EQCRDVC.OPT = 'R';
        S000_CALL_EQZRDVC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_EOF_DVC_FLG);
        if (WS_EOF_DVC_FLG != 'Y') {
            B010_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_EOF_DVC_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            B010_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            EQCRDVC.FUNC = 'B';
            EQCRDVC.OPT = 'R';
            S000_CALL_EQZRDVC();
            if (pgmRtn) return;
        }
        EQCRDVC.FUNC = 'B';
        EQCRDVC.OPT = 'E';
        S000_CALL_EQZRDVC();
        if (pgmRtn) return;
    }
    public void B020_ADD_APPLY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCSDVC.DATA.PROC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (EQCSDVC.DATA.PROC_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_PROCDT_MUST_BIGGER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B020_01_WRITE_EQTDVC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUT_DATA1);
        WS_OUT_DATA1.WS_OUT_DIVFLG = EQCSDVC.DATA.DICP_FLG;
        WS_OUT_DATA1.WS_OUT_DIVDT = EQCSDVC.DATA.DICP_DT;
        WS_OUT_DATA1.WS_OUT_PROCDT = EQCSDVC.DATA.PROC_DT;
        R000_NFIANCE_HIS();
        if (pgmRtn) return;
    }
    public void B030_APPLY_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (EQCSDVC.DATA.DT_OLD == EQCSDVC.DATA.PROC_DT) {
            R000_GET_STS();
            if (pgmRtn) return;
            if (EQRDVC.REC_STS == 'R' 
                || EQRDVC.REC_STS == 'P') {
                if (EQCSDVC.DATA.DICP_DT == EQRDVC.KEY.DIV_CPN_DT 
                    && EQCSDVC.DATA.PROC_DT == EQRDVC.PROC_DT 
                    && EQCSDVC.DATA.CPN_TYP == EQRDVC.CPN_TYP 
                    && EQCSDVC.DATA.EX_CL_PT == EQRDVC.EX_CAL_PCT 
                    && EQCSDVC.DATA.CO_CL_PT == EQRDVC.CO_CAL_PCT 
                    && EQCSDVC.DATA.IN_CL_PT == EQRDVC.IN_CAL_PCT 
                    && EQCSDVC.DATA.O_CL_PT == EQRDVC.OT_CAL_PCT) {
                    WS_MSGID = EQCMSG_ERROR_MSG.EQ_DATA_NO_CHAGE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    EQRDVC.REQ_DT = SCCGWA.COMM_AREA.AC_DATE;
                    EQRDVC.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
                    EQRDVC.PROC_DT = EQCSDVC.DATA.PROC_DT;
                    EQRDVC.CPN_TYP = EQCSDVC.DATA.CPN_TYP;
                    EQRDVC.REC_STS = 'C';
                    EQRDVC.EX_CAL_PCT = EQCSDVC.DATA.EX_CL_PT;
                    EQRDVC.CO_CAL_PCT = EQCSDVC.DATA.CO_CL_PT;
                    EQRDVC.IN_CAL_PCT = EQCSDVC.DATA.IN_CL_PT;
                    EQRDVC.OT_CAL_PCT = EQCSDVC.DATA.O_CL_PT;
                    EQRDVC.UPDATE_FLG = 'M';
                    EQCRDVC.FUNC = 'U';
                    S000_CALL_EQZRDVC();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, WS_OUT_DATA1);
                    WS_OUT_DATA1.WS_OUT_DIVFLG = EQCSDVC.DATA.DICP_FLG;
                    WS_OUT_DATA1.WS_OUT_DIVDT = EQCSDVC.DATA.DICP_DT;
                    WS_OUT_DATA1.WS_OUT_PROCDT = EQCSDVC.DATA.PROC_DT;
                    R000_NFIANCE_HIS();
                    if (pgmRtn) return;
                }
            } else {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_ERR_RECSTS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (EQCSDVC.DATA.PROC_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_PROCDT_MUST_BIGGER;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, EQRDVC);
            EQRDVC.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
            EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
            EQRDVC.PROC_DT = EQCSDVC.DATA.PROC_DT;
            T000_READ_EQTDVC();
            if (pgmRtn) return;
            if (WS_DVC_FLG == 'Y') {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_PROCDT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, EQRDVC);
            EQRDVC.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
            EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
            EQRDVC.PROC_DT = EQCSDVC.DATA.DT_OLD;
            T000_READUP_EQTDVC();
            if (pgmRtn) return;
            if (EQRDVC.REC_STS == 'R' 
                || EQRDVC.REC_STS == 'P') {
                EQRDVC.REQ_DT = SCCGWA.COMM_AREA.AC_DATE;
                EQRDVC.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
                EQRDVC.PROC_DT = EQCSDVC.DATA.PROC_DT;
                EQRDVC.CPN_TYP = EQCSDVC.DATA.CPN_TYP;
                EQRDVC.REC_STS = 'C';
                EQRDVC.EX_CAL_PCT = EQCSDVC.DATA.EX_CL_PT;
                EQRDVC.CO_CAL_PCT = EQCSDVC.DATA.CO_CL_PT;
                EQRDVC.IN_CAL_PCT = EQCSDVC.DATA.IN_CL_PT;
                EQRDVC.OT_CAL_PCT = EQCSDVC.DATA.O_CL_PT;
                EQRDVC.UPDATE_FLG = 'M';
                T000_REWRITE_EQTDVC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, WS_OUT_DATA1);
                WS_OUT_DATA1.WS_OUT_DIVFLG = EQCSDVC.DATA.DICP_FLG;
                WS_OUT_DATA1.WS_OUT_DIVDT = EQCSDVC.DATA.DICP_DT;
                WS_OUT_DATA1.WS_OUT_PROCDT = EQCSDVC.DATA.PROC_DT;
                R000_NFIANCE_HIS();
                if (pgmRtn) return;
            } else {
                WS_MSGID = EQCMSG_ERROR_MSG.EQ_ERR_RECSTS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_APPLY_DELETE_PROC() throws IOException,SQLException,Exception {
        R000_GET_STS();
        if (pgmRtn) return;
        if (EQRDVC.REC_STS == 'R' 
            || EQRDVC.REC_STS == 'P') {
            EQRDVC.REC_STS = 'D';
            EQCRDVC.FUNC = 'U';
            S000_CALL_EQZRDVC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, WS_OUT_DATA1);
            WS_OUT_DATA1.WS_OUT_DIVFLG = EQCSDVC.DATA.DICP_FLG;
            WS_OUT_DATA1.WS_OUT_DIVDT = EQCSDVC.DATA.DICP_DT;
            WS_OUT_DATA1.WS_OUT_PROCDT = EQCSDVC.DATA.PROC_DT;
            R000_NFIANCE_HIS();
            if (pgmRtn) return;
        } else {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_ERR_RECSTS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRDVC);
        IBS.init(SCCGWA, EQCRDVC);
        EQRDVC.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
        EQRDVC.PROC_DT = EQCSDVC.DATA.PROC_DT;
        EQCRDVC.FUNC = 'C';
        S000_CALL_EQZRDVC();
        if (pgmRtn) return;
        B050_01_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        B050_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B060_BONUS_PROC() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'N';
        IBS.init(SCCGWA, EQRDVC);
        IBS.init(SCCGWA, EQCRDVC);
        EQRDVC.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
        EQRDVC.PROC_DT = EQCSDVC.DATA.PROC_DT;
        EQCRDVC.FUNC = 'C';
        S000_CALL_EQZRDVC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSDVC.DATA.EQ_AC;
        T000_READ_EQTACT_BY_EQAC();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            WS_TXN_PRODCD = EQRACT.PROD_CD;
            if (EQRACT.EQ_TYP == '1' 
                || EQRACT.EQ_TYP == '3') {
                R000_CHECK_DIV_AC();
                if (pgmRtn) return;
                if (WS_CI_SAME_FLG == 'N') {
                    WS_MSGID = EQCMSG_ERROR_MSG.EQ_DIV_CINO_NOT_SAME;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("01")) {
                WS_BR_AC = K_BRAC_FH1;
                WS_DIV_BR = K_DIV_BR1;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("51")) {
                WS_BR_AC = K_BRAC_FH2;
                WS_DIV_BR = K_DIV_BR2;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("52")) {
                WS_BR_AC = K_BRAC_FH3;
                WS_DIV_BR = K_DIV_BR3;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("53")) {
                WS_BR_AC = K_BRAC_FH4;
                WS_DIV_BR = K_DIV_BR4;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("54")) {
                WS_BR_AC = K_BRAC_FH5;
                WS_DIV_BR = K_DIV_BR5;
            } else {
            }
            if (EQCSDVC.DATA.FH_FLG != 'Y') {
                WS_EQ_QTY = EQRACT.EQ_QTY;
                WS_FRZ_QTY = EQRACT.FRZ_QTY;
                if (EQRACT.EQ_TYP == '1') {
                    WS_DICP_PCT = EQRDVC.EX_CAL_PCT;
                } else if (EQRACT.EQ_TYP == '2') {
                    WS_DICP_PCT = EQRDVC.CO_CAL_PCT;
                } else if (EQRACT.EQ_TYP == '3') {
                    WS_DICP_PCT = EQRDVC.IN_CAL_PCT;
                } else if (EQRACT.EQ_TYP == '4') {
                    WS_DICP_PCT = EQRDVC.OT_CAL_PCT;
                } else {
                }
                CEP.TRC(SCCGWA, WS_DICP_PCT);
                if (EQRACT.FRZ_QTY == 0) {
                    WS_DC_CAL_AMQT = WS_DICP_PCT * EQRACT.EQ_QTY / 100;
                    bigD = new BigDecimal(WS_DC_CAL_AMQT);
                    WS_DC_CAL_AMQT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    if (EQRACT.EQ_TYP == '2') {
                        WS_TXN_TAX = 0;
                    } else {
                        WS_TXN_TAX = WS_DC_CAL_AMQT * K_TAX_RAT / 100;
                        bigD = new BigDecimal(WS_TXN_TAX);
                        WS_TXN_TAX = bigD.setScale(6, RoundingMode.HALF_UP).doubleValue();
                    }
                } else {
                    if (EQRACT.FRZ_QTY > EQRACT.EQ_QTY) {
                        WS_DC_CAL_AMQT1 = WS_DICP_PCT * EQRACT.EQ_QTY / 100;
                        bigD = new BigDecimal(WS_DC_CAL_AMQT1);
                        WS_DC_CAL_AMQT1 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        WS_DC_CAL_AMQT2 = 0;
                    } else {
                        WS_DC_CAL_AMQT1 = WS_DICP_PCT * EQRACT.FRZ_QTY / 100;
                        bigD = new BigDecimal(WS_DC_CAL_AMQT1);
                        WS_DC_CAL_AMQT1 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        WS_TMP_QTY = EQRACT.EQ_QTY - EQRACT.FRZ_QTY;
                        WS_DC_CAL_AMQT2 = WS_DICP_PCT * WS_TMP_QTY / 100;
                        bigD = new BigDecimal(WS_DC_CAL_AMQT2);
                        WS_DC_CAL_AMQT2 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    }
                    WS_DC_CAL_AMQT = WS_DC_CAL_AMQT1 + WS_DC_CAL_AMQT2;
                    if (EQRACT.EQ_TYP == '2') {
                        WS_TXN_TAX = 0;
                    } else {
                        WS_TXN_TAX1 = WS_DC_CAL_AMQT1 * K_TAX_RAT / 100;
                        bigD = new BigDecimal(WS_TXN_TAX1);
                        WS_TXN_TAX1 = bigD.setScale(6, RoundingMode.HALF_UP).doubleValue();
                        WS_TXN_TAX2 = WS_DC_CAL_AMQT2 * K_TAX_RAT / 100;
                        bigD = new BigDecimal(WS_TXN_TAX2);
                        WS_TXN_TAX2 = bigD.setScale(6, RoundingMode.HALF_UP).doubleValue();
                        WS_TXN_TAX = WS_TXN_TAX1 + WS_TXN_TAX2;
                        bigD = new BigDecimal(WS_TXN_TAX);
                        WS_TXN_TAX = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    }
                }
            } else {
                IBS.init(SCCGWA, EQRDVCD);
                WS_DVCD_FLG = ' ';
                EQRDVCD.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
                EQRDVCD.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
                EQRDVCD.EQ_AC = EQCSDVC.DATA.EQ_AC;
                EQRDVCD.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
                EQRDVCD.DIV_CPN_STS = '2';
                T000_READ_EQTDVCD();
                if (pgmRtn) return;
                if (WS_DVCD_FLG == 'Y') {
                    WS_EQ_QTY = EQRDVCD.EQ_QTY;
                    WS_FRZ_QTY = EQRDVCD.FRZ_QTY;
                    WS_DICP_PCT = EQRDVCD.DIV_CPN_PCT;
                    WS_DC_CAL_AMQT = EQRDVCD.DC_CAL_AMQT;
                    WS_DC_CAL_AMQT1 = EQRDVCD.DC_ACT_AMQT;
                    WS_TXN_TAX = EQRDVCD.TAX;
                    WS_TXN_TAX1 = EQRDVCD.FRZ_TAX;
                    WS_DC_CAL_AMQT2 = WS_DC_CAL_AMQT - WS_DC_CAL_AMQT1;
                    WS_TXN_TAX2 = WS_TXN_TAX - WS_TXN_TAX1;
                }
            }
            WS_DIV_AMT = WS_DC_CAL_AMQT - WS_TXN_TAX;
            CEP.TRC(SCCGWA, WS_DC_CAL_AMQT);
            CEP.TRC(SCCGWA, WS_TXN_TAX);
            WS_TXN_DICP_PCT = WS_DICP_PCT;
            WS_TXN_DICP_AMT = WS_DC_CAL_AMQT;
            EQRACT.LS_DIVIDEND_DT = EQCSDVC.DATA.DICP_DT;
            EQRACT.LS_DIV_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
            EQRACT.LS_DIV_AMT = WS_DIV_AMT;
            T000_REWRITE_EQTACT();
            if (pgmRtn) return;
            R000_WRITE_EQTDVCD();
            if (pgmRtn) return;
            R000_GEN_DIVCH();
            if (pgmRtn) return;
            if (!EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                B060_01_HANDLE_WDZ();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_SENDST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRDVC);
        IBS.init(SCCGWA, EQCRDVC);
        EQRDVC.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
        EQRDVC.PROC_DT = EQCSDVC.DATA.PROC_DT;
        EQCRDVC.FUNC = 'C';
        S000_CALL_EQZRDVC();
        if (pgmRtn) return;
        WS_CPN_TYP = EQRDVC.CPN_TYP;
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSDVC.DATA.EQ_AC;
        T000_READ_EQTACT_BY_EQAC();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            WS_TXN_PRODCD = EQRACT.PROD_CD;
            if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("01")) {
                WS_BR_AC = K_BRAC_FH1;
                WS_DIV_BR = K_DIV_BR1;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("51")) {
                WS_BR_AC = K_BRAC_FH2;
                WS_DIV_BR = K_DIV_BR2;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("52")) {
                WS_BR_AC = K_BRAC_FH3;
                WS_DIV_BR = K_DIV_BR3;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("53")) {
                WS_BR_AC = K_BRAC_FH4;
                WS_DIV_BR = K_DIV_BR4;
            } else if (EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase("54")) {
                WS_BR_AC = K_BRAC_FH5;
                WS_DIV_BR = K_DIV_BR5;
            } else {
            }
            if (EQCSDVC.DATA.FH_FLG != 'Y') {
                WS_EQ_QTY = EQRACT.EQ_QTY;
                WS_FRZ_QTY = EQRACT.FRZ_QTY;
                if (EQRACT.EQ_TYP == '1') {
                    WS_DICP_PCT = EQRDVC.EX_CAL_PCT;
                } else if (EQRACT.EQ_TYP == '2') {
                    WS_DICP_PCT = EQRDVC.CO_CAL_PCT;
                } else if (EQRACT.EQ_TYP == '3') {
                    WS_DICP_PCT = EQRDVC.IN_CAL_PCT;
                } else if (EQRACT.EQ_TYP == '4') {
                    WS_DICP_PCT = EQRDVC.OT_CAL_PCT;
                } else {
                }
                CEP.TRC(SCCGWA, WS_DICP_PCT);
                WS_DC_CAL_AMQT = WS_DICP_PCT * EQRACT.EQ_QTY / 100;
                bigD = new BigDecimal(WS_DC_CAL_AMQT);
                WS_DC_CAL_AMQT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, WS_DC_CAL_AMQT);
                WS_AMT1 = WS_DC_CAL_AMQT;
                if (WS_CHAR == null) WS_CHAR = "";
                JIBS_tmp_int = WS_CHAR.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) WS_CHAR += " ";
                if (WS_CHAR.substring(0, 14).trim().length() == 0) WS_AMT2 = 0;
                else WS_AMT2 = Long.parseLong(WS_CHAR.substring(0, 14));
                WS_DC_CAL_AMQT = WS_AMT2;
                if (WS_CPN_TYP == '2' 
                    && EQRACT.EQ_TYP != '2') {
                    WS_TXN_TAX = WS_DC_CAL_AMQT * K_TAX_RAT / 100;
                    bigD = new BigDecimal(WS_TXN_TAX);
                    WS_TXN_TAX = bigD.setScale(6, RoundingMode.HALF_UP).doubleValue();
                } else {
                    WS_TXN_TAX = 0;
                }
            } else {
                IBS.init(SCCGWA, EQRDVCD);
                WS_DVCD_FLG = ' ';
                EQRDVCD.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
                EQRDVCD.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
                EQRDVCD.EQ_AC = EQCSDVC.DATA.EQ_AC;
                EQRDVCD.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
                EQRDVCD.DIV_CPN_STS = '2';
                T000_READ_EQTDVCD();
                if (pgmRtn) return;
                if (WS_DVCD_FLG == 'Y') {
                    WS_EQ_QTY = EQRDVCD.EQ_QTY;
                    WS_DICP_PCT = EQRDVCD.DIV_CPN_PCT;
                    WS_DC_CAL_AMQT = EQRDVCD.DC_CAL_AMQT;
                    WS_DC_CAL_AMQT1 = EQRDVCD.DC_ACT_AMQT;
                    WS_TXN_TAX = EQRDVCD.TAX;
                    WS_DC_CAL_AMQT2 = WS_DC_CAL_AMQT - WS_DC_CAL_AMQT1;
                }
            }
            CEP.TRC(SCCGWA, WS_DC_CAL_AMQT);
            CEP.TRC(SCCGWA, WS_TXN_TAX);
            WS_TXN_TAX1 = 0;
            WS_TXN_DICP_PCT = WS_DICP_PCT;
            WS_TXN_DICP_AMT = WS_DC_CAL_AMQT;
            EQRACT.LS_COUPON_DT = EQCSDVC.DATA.DICP_DT;
            EQRACT.LS_CPN_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
            EQRACT.LS_CPN_QTY = WS_DC_CAL_AMQT;
            EQRACT.EQ_QTY = EQRACT.EQ_QTY + WS_DC_CAL_AMQT;
            T000_REWRITE_EQTACT();
            if (pgmRtn) return;
            R000_WRITE_EQTDVCD();
            if (pgmRtn) return;
            R000_WRITE_EQTACTD();
            if (pgmRtn) return;
            if (WS_TXN_DICP_AMT != 0) {
                R000_FINANCE_HIS();
                if (pgmRtn) return;
            }
            R000_GEN_SSVCH();
            if (pgmRtn) return;
            if (!EQCSDVC.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                B070_03_HANDLE_WDZ();
                if (pgmRtn) return;
            }
        }
    }
    public void B080_CHECK_PROC() throws IOException,SQLException,Exception {
        R000_GET_STS();
        if (pgmRtn) return;
        if (EQRDVC.UPDTBL_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_ERR_CHKTLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQRDVC.REC_STS != EQCSDVC.DATA.REC_STS) {
            WS_MSGID = EQCMSG_ERROR_MSG.EQ_INVALID_REC_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQCSDVC.DATA.REC_STS == 'C') {
            if (EQCSDVC.DATA.FH_FLG == 'Y') {
                EQRDVC.REC_STS = 'P';
            } else {
                EQRDVC.REC_STS = 'R';
            }
            EQCRDVC.FUNC = 'U';
            S000_CALL_EQZRDVC();
            if (pgmRtn) return;
        } else {
            if (EQCSDVC.DATA.FH_FLG == 'Y') {
                EQCRDVC.FUNC = 'D';
                S000_CALL_EQZRDVC();
                if (pgmRtn) return;
            } else {
                EQRDVC.REC_STS = 'R';
                EQCRDVC.FUNC = 'U';
                S000_CALL_EQZRDVC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ200_OPT_8200);
        EQCOQ200_OPT_8200.EQ_BKID = EQRDVC.KEY.EQ_BKID;
        EQCOQ200_OPT_8200.DICP_FLG = EQRDVC.KEY.DIV_CPN_FLG;
        EQCOQ200_OPT_8200.DICP_DT = EQRDVC.KEY.DIV_CPN_DT;
        EQCOQ200_OPT_8200.PROC_DT = EQRDVC.PROC_DT;
        EQCOQ200_OPT_8200.REQ_DT = EQRDVC.REQ_DT;
        EQCOQ200_OPT_8200.EX_CL_PT = EQRDVC.EX_CAL_PCT;
        EQCOQ200_OPT_8200.CO_CL_PT = EQRDVC.CO_CAL_PCT;
        EQCOQ200_OPT_8200.IN_CL_PT = EQRDVC.IN_CAL_PCT;
        EQCOQ200_OPT_8200.O_CL_PT = EQRDVC.OT_CAL_PCT;
        EQCOQ200_OPT_8200.CPN_TYP = EQRDVC.CPN_TYP;
        EQCOQ200_OPT_8200.REC_STS = EQRDVC.REC_STS;
        EQCOQ200_OPT_8200.UPDATE_FLG = EQRDVC.UPDATE_FLG;
        CEP.TRC(SCCGWA, EQCOQ200_OPT_8200.UPDATE_FLG);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCOQ200_OPT_8200);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_01_WRITE_EQTDVC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRDVC);
        IBS.init(SCCGWA, EQCRDVC);
        EQRDVC.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRDVC.KEY.DIV_CPN_FLG = EQCSDVC.DATA.DICP_FLG;
        EQRDVC.KEY.DIV_CPN_DT = EQCSDVC.DATA.DICP_DT;
        EQRDVC.PROC_DT = EQCSDVC.DATA.PROC_DT;
        EQRDVC.REQ_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRDVC.EX_CAL_PCT = EQCSDVC.DATA.EX_CL_PT;
        EQRDVC.CO_CAL_PCT = EQCSDVC.DATA.CO_CL_PT;
        EQRDVC.IN_CAL_PCT = EQCSDVC.DATA.IN_CL_PT;
        EQRDVC.OT_CAL_PCT = EQCSDVC.DATA.O_CL_PT;
        EQRDVC.CPN_TYP = EQCSDVC.DATA.CPN_TYP;
        EQRDVC.REQ_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRDVC.REQ_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRDVC.CHK_TLR = "" + 0;
        JIBS_tmp_int = EQRDVC.CHK_TLR.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) EQRDVC.CHK_TLR = "0" + EQRDVC.CHK_TLR;
        EQRDVC.AUTH_TLR = " ";
        EQRDVC.REC_STS = 'C';
        EQRDVC.UPDATE_FLG = 'N';
        EQRDVC.DIV_CPN_FCNT = 0;
        EQRDVC.DIV_CPN_FAMT = 0;
        EQRDVC.DIV_CPN_SCNT = 0;
        EQRDVC.DIV_CPN_SAMT = 0;
        EQRDVC.REMARK = " ";
        EQRDVC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRDVC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRDVC.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRDVC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRDVC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRDVC.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRDVC.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRDVC.TS = "0" + EQRDVC.TS;
        EQCRDVC.FUNC = 'A';
        S000_CALL_EQZRDVC();
        if (pgmRtn) return;
    }
    public void B050_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ205_OPT_8205);
        EQCOQ205_OPT_8205.EQ_BKID = EQRDVC.KEY.EQ_BKID;
        EQCOQ205_OPT_8205.DICP_FLG = EQRDVC.KEY.DIV_CPN_FLG;
        EQCOQ205_OPT_8205.DICP_DT = EQRDVC.KEY.DIV_CPN_DT;
        EQCOQ205_OPT_8205.PROC_DT = EQRDVC.PROC_DT;
        EQCOQ205_OPT_8205.CPN_TYP = EQRDVC.CPN_TYP;
        EQCOQ205_OPT_8205.EX_CL_PT = EQRDVC.EX_CAL_PCT;
        EQCOQ205_OPT_8205.CO_CL_PT = EQRDVC.CO_CAL_PCT;
        EQCOQ205_OPT_8205.IN_CL_PT = EQRDVC.IN_CAL_PCT;
        EQCOQ205_OPT_8205.O_CL_PT = EQRDVC.OT_CAL_PCT;
        EQCOQ205_OPT_8205.DICP_FNT = EQRDVC.DIV_CPN_FCNT;
        EQCOQ205_OPT_8205.DICP_FMT = EQRDVC.DIV_CPN_FAMT;
        EQCOQ205_OPT_8205.DICP_SNT = EQRDVC.DIV_CPN_SCNT;
        EQCOQ205_OPT_8205.DICP_SMT = EQRDVC.DIV_CPN_SAMT;
        EQCOQ205_OPT_8205.REQ_DT = EQRDVC.REQ_DT;
        EQCOQ205_OPT_8205.A_TXN_DT = 0;
        EQCOQ205_OPT_8205.REC_STS = EQRDVC.REC_STS;
        EQCOQ205_OPT_8205.REMARK = EQRDVC.REMARK;
    }
    public void B050_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = EQCOQ205_OPT_8205;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_HANDLE_WDZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        WS_BVT_FLG = ' ';
        EQRBVT.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQCSDVC.DATA.EQ_AC;
        EQRBVT.PSBK_STS = 'N';
        T000_READUP_EQTBVT();
        if (pgmRtn) return;
        WS_UPT_NO = EQRBVT.PT_BOCNT + EQRBVT.UT_BOCNT + 1;
        EQRBVT.UPT_CNT = EQRBVT.UPT_CNT + 1;
        EQRBVT.UT_BOCNT = EQRBVT.UT_BOCNT + 1;
        EQRBVT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRBVT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_EQTBVT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRWDZ);
        EQRWDZ.KEY.EQ_BKID = EQCSDVC.DATA.EQ_BKID;
        EQRWDZ.KEY.EQ_AC = EQCSDVC.DATA.EQ_AC;
        EQRWDZ.KEY.PSBK_NO = EQRBVT.KEY.PSBK_NO;
        EQRWDZ.KEY.UPT_TYP = '3';
        EQRWDZ.KEY.UPT_NO = WS_UPT_NO;
        EQRWDZ.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRWDZ.TXN_DATE = EQCSDVC.DATA.DICP_DT;
        EQRWDZ.TXN_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = EQRWDZ.TXN_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) EQRWDZ.TXN_CODE = "0" + EQRWDZ.TXN_CODE;
        EQRWDZ.TXN_MMO = "088";
        EQRWDZ.CCY = K_CCY_CNY;
        EQRWDZ.CCY_TYPE = '1';
        EQRWDZ.EQ_TXN_TYPE = '9';
        EQRWDZ.TXN_QTY = 0;
        EQRWDZ.TXN_PRICE = 0;
        EQRWDZ.TXN_AMT = 0;
        EQRWDZ.CAL_PCT = WS_TXN_DICP_PCT;
        EQRWDZ.BAL_QTY = WS_DIV_AMT;
        EQRWDZ.ADD_DTL = " ";
        EQRWDZ.TEL_DTL = " ";
        EQRWDZ.AC_DTL = " ";
        EQRWDZ.PRT_FLG = 'N';
        EQRWDZ.REMARK = " ";
        EQRWDZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRWDZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRWDZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRWDZ.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRWDZ.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRWDZ.TS = "0" + EQRWDZ.TS;
        T000_WRITE_EQTWDZ();
        if (pgmRtn) return;
    }
    public void B060_SEND_REC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B060-SEND-REC-CN");
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
