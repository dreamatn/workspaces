package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.DP.*;
import com.hisun.CI.*;
import java.text.DecimalFormat;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPPRTF {
    TDCQPB_DATA DATA;
    TDCQYBT_DATA DATA;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DecimalFormat df;
    DBParm TDTCDI_RD;
    DBParm TDTCMST_RD;
    DBParm TDTPBP_RD;
    brParm TDTPBP_BR = new brParm();
    brParm TDTYBTP_BR = new brParm();
    DBParm TDTYBTP_RD;
    DBParm TDTBVT_RD;
    DBParm TDTSMST_RD;
    DBParm TDTIREV_RD;
    boolean pgmRtn = false;
    String K_YBT_FMT = "TD001";
    String K_PB_FMT = "TD002";
    String K_BV_FMT = "TD003";
    String K_YBTF_FMT = "TD004";
    String K_PBF_FMT = "TD005";
    String K_MS_FMT = "TD006";
    String K_ZSS_FMT = "TD009";
    String K_AP_MMO = "TD";
    String K_BV_FMT_TTZ = "TD013";
    String K_BV_FMT_TDH = "TD014";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    int WS_AC_SEQ = 0;
    short WS_LAST_PSBK_POS = 0;
    short WS_THIS_PSBK_POS = 0;
    String WS_AMT = " ";
    int WS_CNT = 0;
    int WS_PSBK_POS = 0;
    int WS_QYBT_CNT = 0;
    double WS_INT_RATE = 0;
    int WS_TMP_PSBK_POS = 0;
    int WS_LAST_POS = 0;
    short WS_TMP_PBP_POS = 0;
    short WS_POS_J = 0;
    short WS_TMP_POS = 0;
    short WS_TMP_S = 0;
    short WS_POS_J_R = 0;
    short WS_PRT_NUM = 0;
    short WS_X = 0;
    int WS_OPEN_BR = 0;
    short WS_RP_STAR = 0;
    short WS_RP_END = 0;
    short WS_RP_STAR_P = 0;
    short WS_RP_END_P = 0;
    double WS_SUM_BAL = 0;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    char WS_YBT_AC_FLAG = ' ';
    char WS_CNT_FLG = ' ';
    char WS_QPB_FLG = ' ';
    int WS_DATE1 = 0;
    TDZPPRTF_REDEFINES36 REDEFINES36 = new TDZPPRTF_REDEFINES36();
    int WS_DATE2 = 0;
    TDZPPRTF_REDEFINES41 REDEFINES41 = new TDZPPRTF_REDEFINES41();
    int WS_DATE3 = 0;
    int WS_TERM_DT = 0;
    TDZPPRTF_REDEFINES47 REDEFINES47 = new TDZPPRTF_REDEFINES47();
    short WS_PRT_LINE = 0;
    short WS_LINE = 0;
    short WS_PRINT_CNT = 0;
    short WS_CTRL_CNT = 0;
    char WS_YBT_PRT_OVER = ' ';
    char WS_MORE_RCD = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDCQYBT TDCQYBT = new TDCQYBT();
    TDCQPB TDCQPB = new TDCQPB();
    TDCQYBTF TDCQYBTF = new TDCQYBTF();
    TDCQPBF TDCQPBF = new TDCQPBF();
    TDCQMS TDCQMS = new TDCQMS();
    TDCQBV TDCQBV = new TDCQBV();
    TDRSMST TDRSMST = new TDRSMST();
    TDRIREV TDRIREV = new TDRIREV();
    TDRCDI TDRCDI = new TDRCDI();
    TDRYBTP TDRYBTP = new TDRYBTP();
    TDRPBP TDRPBP = new TDRPBP();
    TDRBVT TDRBVT = new TDRBVT();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCUPARM TDCUPARM = new TDCUPARM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDRCMST TDRCMST = new TDRCMST();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    TDCPPRTF TDCPPRTF;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCPPRTF TDCPPRTF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPPRTF = TDCPPRTF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZPPRTF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, TDRPBP);
        CEP.TRC(SCCGWA, TDCPPRTF.OPT);
        CEP.TRC(SCCGWA, TDCPPRTF.AC);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_CD);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_NO);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_OPT);
        CEP.TRC(SCCGWA, TDCPPRTF.F_LINE);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_LINES);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_TOLINE);
        CEP.TRC(SCCGWA, TDCPPRTF.AC_SEQ);
        WS_CTRL_CNT = 40;
        TDCQYBT.PRT_FLG = ' ';
        B800_CHECK_YBTP_INPUT();
        if (pgmRtn) return;
        if (TDCPPRTF.OPT != '3' 
            && TDCPPRTF.OPT != '6') {
            IBS.init(SCCGWA, TDCUPARM);
            TDCUPARM.FUNC = 'I';
            S000_CALL_TDZUPARM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.YBT_LINE_LMT);
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCPPRTF.BV_TYP == '1') {
            B110_GET_BV_TYP_UNT();
            if (pgmRtn) return;
        }
        DPCPARMP.AC_TYPE = TDCPPRTF.PRDAC_CD;
        CEP.TRC(SCCGWA, TDCPPRTF.OPT);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_TYP);
        if (TDCPPRTF.OPT == '1' 
                && TDCPPRTF.BV_TYP == '1') {
            B000_PRT_YBT_TITTLE();
            if (pgmRtn) return;
            B100_PRINT_YBT();
            if (pgmRtn) return;
        } else if (TDCPPRTF.OPT == '2' 
                && TDCPPRTF.BV_TYP == '1') {
            B200_REPLACE_YBT_RPRT();
            if (pgmRtn) return;
        } else if (TDCPPRTF.OPT == '1' 
                && TDCPPRTF.BV_TYP == '2') {
            B300_PRINT_PB();
            if (pgmRtn) return;
        } else if (TDCPPRTF.OPT == '2' 
                && TDCPPRTF.BV_TYP == '2') {
            B400_REPLACE_PB();
            if (pgmRtn) return;
        } else if (TDCPPRTF.OPT == '3') {
            B500_PRINT_BV();
            if (pgmRtn) return;
        } else if (TDCPPRTF.OPT == '5' 
                && (TDCPPRTF.BV_TYP == '1' 
                && WS_YBT_AC_FLAG == 'Y')) {
            if (TDCPPRTF.RP_OPT == 'B') {
                B800_REPRINT_YBTP_BOT();
                if (pgmRtn) return;
            }
            if (TDCPPRTF.RP_OPT == 'S') {
                B800_REPRINT_YBTP_SAME();
                if (pgmRtn) return;
            }
            if (TDCPPRTF.RP_OPT == 'L') {
                B800_REPRINT_YBTP_TOLINE();
                if (pgmRtn) return;
            }
        } else if (TDCPPRTF.OPT == '5' 
                && (TDCPPRTF.BV_TYP == '1' 
                && WS_YBT_AC_FLAG == 'N')) {
            if (TDCPPRTF.RP_OPT == 'B' 
                || TDCPPRTF.RP_OPT == 'S') {
                B800_REPRINT_PBP();
                if (pgmRtn) return;
            }
        } else if (TDCPPRTF.OPT == '6') {
            if (TDCPPRTF.BV_TYP == '1' 
                && WS_YBT_AC_FLAG == 'Y') {
                B000_PRT_YBT_TITTLE();
                if (pgmRtn) return;
            }
            if (TDCPPRTF.BV_TYP == '2') {
                B000_PRT_PBP_TITTLE();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_OPT, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B800_REPRINT_PBP() throws IOException,SQLException,Exception {
        if (TDCPPRTF.RP_OPT == 'B') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_PB_NOT_BOT_RPRT, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRCDI);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRPBP);
        TDCQPB.CNT = 0;
        DATA = new TDCQPB_DATA();
        TDCQPB.DATA.add(DATA);
        WS_QPB_FLG = 'N';
        TDRCDI.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRSMST.AC_NO = TDCPPRTF.AC;
        if (TDCPPRTF.RP_OPT == 'B') {
            T000_READ_UPDATE_CDI();
            if (pgmRtn) return;
            WS_LAST_POS = TDRCDI.PSBK_POS + TDCPPRTF.RP_LINES;
            TDRPBP.KEY.AC_NO = TDCPPRTF.AC;
            T000_READ_PBP_LAST();
            if (pgmRtn) return;
            if (TDRPBP.KEY.PROC_SEQ > TDRCDI.PSBK_POS) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REM_NOPRT_PLS_PRT, TDCPPRTF.RC_MSG);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            T000_READ_TDTCDI();
            if (pgmRtn) return;
            WS_TMP_PBP_POS = (short) (TDCPPRTF.F_LINE - 1);
        }
        T000_READ_SMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        T000_READ_BVT();
        if (pgmRtn) return;
        WS_RP_STAR_P = TDCPPRTF.F_LINE;
        WS_RP_END_P = (short) (WS_RP_STAR_P + TDCPPRTF.RP_LINES - 1);
        if (TDCPPRTF.RP_OPT == 'B' 
            && WS_LAST_POS > TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RP_L_M_DUE_L, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRPBP);
        T000_STARTBR_PBP_REPRT();
        if (pgmRtn) return;
        T000_READNEXT_PBP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_RPT_DATA, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && TDRCDI.PSBK_POS <= TDCUPARM.PARM_DATA.YBT_LINE_LMT 
            && WS_QPB_FLG != 'Y') {
            R000_PRT_INF_TLZ();
            if (pgmRtn) return;
            if (TDCPPRTF.RP_OPT == 'B') {
                T000_REWRITE_PBP();
                if (pgmRtn) return;
            }
            T000_READNEXT_PBP();
            if (pgmRtn) return;
        }
        T000_ENDBR_PBP();
        if (pgmRtn) return;
        if (TDCPPRTF.RP_OPT == 'B') {
            T000_REWRITE_CDI();
            if (pgmRtn) return;
        }
        if (TDCQPB.CNT > 0) {
            B900_OUTPUT_PB_INF();
            if (pgmRtn) return;
        }
    }
    public void B800_REPRINT_PBP_TOLINE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCDI);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, TDRPBP);
        IBS.init(SCCGWA, TDRBVT);
        TDCQPB.CNT = 0;
        DATA = new TDCQPB_DATA();
        TDCQPB.DATA.add(DATA);
        WS_QPB_FLG = 'N';
        TDRCDI.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRSMST.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRPBP.KEY.AC_NO = TDCPPRTF.AC;
        T000_READ_UPDATE_CDI();
        if (pgmRtn) return;
        T000_READ_SMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        T000_READU_BVT();
        if (pgmRtn) return;
        WS_RP_STAR_P = TDCPPRTF.F_LINE;
        WS_RP_END_P = (short) (WS_RP_STAR_P + TDCPPRTF.RP_LINES - 1);
        WS_POS_J = (short) (TDCPPRTF.RP_TOLINE - TDRBVT.PSBK_POS + TDCPPRTF.RP_LINES - 1);
        WS_POS_J_R = (short) (TDCPPRTF.RP_TOLINE - TDCPPRTF.F_LINE);
        if (TDCPPRTF.RP_TOLINE <= TDRBVT.PSBK_POS) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NEW_L_M_LARGE, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        TDRBVT.PSBK_POS = TDRBVT.PSBK_POS + WS_POS_J;
        T000_UPDATE_BVT();
        if (pgmRtn) return;
        T000_STARTBR_PBP_REPRT();
        if (pgmRtn) return;
        T000_READNEXT_PBP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_RPT_DATA, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDCPPRTF.RP_OPT == 'B' 
            && TDRBVT.PSBK_POS > TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RP_L_M_DUE_L, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_SUM_PBP_BAL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && TDRCDI.PSBK_POS <= TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            R000_PRT_PBP_RPT_TLINE();
            if (pgmRtn) return;
            T000_READNEXT_PBP();
            if (pgmRtn) return;
            TDRPBP.PSBK_POS += WS_POS_J_R;
            T000_REWRITE_PBP();
            if (pgmRtn) return;
        }
        T000_ENDBR_PBP();
        if (pgmRtn) return;
        TDRCDI.PSBK_POS = (short) TDRBVT.PSBK_POS;
        T000_REWRITE_CDI();
        if (pgmRtn) return;
        if (TDCQPB.CNT > 0) {
            B900_OUTPUT_PB_INF();
            if (pgmRtn) return;
        }
    }
    public void B800_CHECK_YBTP_INPUT() throws IOException,SQLException,Exception {
        if (TDCPPRTF.OPT == '5' 
            && TDCPPRTF.F_LINE < 1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NEED_RP_BLINE, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDCPPRTF.OPT == '5' 
            && TDCPPRTF.RP_LINES < 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RP_L_M_T_ZERO, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDCPPRTF.OPT == '5' 
            && TDCPPRTF.RP_LINES > WS_CTRL_CNT) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REP_NEED_LESS_LIMT, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_PRINT_YBT() throws IOException,SQLException,Exception {
        TDRYBTP.KEY.AC_NO = TDCPPRTF.AC;
        T000_STARTBR_YBTP_PRT();
        if (pgmRtn) return;
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_UNPRT_INF, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        TDCQYBT.CNT = 0;
        DATA = new TDCQYBT_DATA();
        TDCQYBT.DATA.add(DATA);
        WS_AC_SEQ = 0;
        WS_QYBT_CNT = 0;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_YBT_PRT_OVER != 'Y') {
            if (TDRYBTP.PSBK_POS >= TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_YBT_FULL_P_CHG, TDCPPRTF.RC_MSG);
                SCCGWA.COMM_AREA.DBIO_FLG = '1';
            } else {
                if (TDRYBTP.PRT_STS == '1') {
                    R000_PRT_OPN_INF();
                    if (pgmRtn) return;
                    TDRYBTP.PRT_STS = '2';
                } else if (TDRYBTP.PRT_STS == '3') {
                    R000_PRT_CLO_INF();
                    if (pgmRtn) return;
                    TDRYBTP.PRT_STS = '4';
                } else if (TDRYBTP.PRT_STS == '5') {
                    R000_PRT_OPN_INF();
                    if (pgmRtn) return;
                    R000_PRT_CLO_INF();
                    if (pgmRtn) return;
                    TDRYBTP.PRT_STS = '4';
                } else {
                }
                T000_REWRITE_YBTP();
                if (pgmRtn) return;
                T000_READNEXT_YBTP();
                if (pgmRtn) return;
                WS_PRINT_CNT = TDCQYBT.CNT;
                if (TDRYBTP.PRT_STS == '1' 
                    || TDRYBTP.PRT_STS == '3') {
                    WS_PRINT_CNT += 1;
                }
                if (TDRYBTP.PRT_STS == '5') {
                    WS_PRINT_CNT += 2;
                }
                CEP.TRC(SCCGWA, "HHH");
                CEP.TRC(SCCGWA, WS_PRINT_CNT);
                CEP.TRC(SCCGWA, WS_CTRL_CNT);
                if (WS_PRINT_CNT > WS_CTRL_CNT) {
                    WS_LINE = (short) (TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE % 2);
                    WS_PRT_LINE = (short) ((TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE - WS_LINE) / 2);
                    CEP.TRC(SCCGWA, WS_LINE);
                    WS_YBT_PRT_OVER = 'Y';
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_PLS_BDZ, TDCPPRTF.RC_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, "CONTROL111");
                CEP.TRC(SCCGWA, TDCQYBT.CNT);
                CEP.TRC(SCCGWA, TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE);
            }
        }
        if (TDRYBTP.PSBK_POS >= TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            if (WS_MORE_RCD == 'Y') {
                TDCQYBT.PRT_FLG = '2';
            }
        } else {
            if (WS_PRINT_CNT > WS_CTRL_CNT 
                && WS_MORE_RCD == 'Y') {
                TDCQYBT.PRT_FLG = '1';
            }
        }
        T000_ENDBR_YBTP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCQYBT.CNT);
        if (TDCQYBT.CNT >= 0) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_PSBK_POS);
            B900_OUTPUT_YBT_INF();
            if (pgmRtn) return;
        }
    }
    public void B110_GET_BV_TYP_UNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPPRTF.AC);
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        T000_READ_BVT1();
        if (pgmRtn) return;
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OLD_BV_ERR, SCCBINF);
        }
        WS_YBT_AC_FLAG = 'Y';
    }
    public void B200_REPLACE_YBT_RPRT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRYBTP);
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCPPRTF.AC;
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        TDRBVT.BV_NO = TDCPPRTF.BV_NO;
        T000_READ_TDCMST();
        if (pgmRtn) return;
        T000_READ_BVT1();
        if (pgmRtn) return;
        if (TDRCMST.STS == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "B900-OUTPUT-QYBTF-INF");
        WS_OPEN_BR = TDRCMST.OWNER_BR;
        R000_GET_BK_NAME();
        if (pgmRtn) return;
        TDCQYBTF.MAIN_AC = TDCPPRTF.AC;
        TDCQYBTF.OPEN_BR = TDRCMST.OWNER_BR;
        TDCQYBTF.OPEN_DATE = TDRCMST.OPEN_DATE;
        TDCQYBTF.OPEN_TLR = TDRCMST.OPEN_TLR;
        TDCQYBTF.DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCQYBTF.BK_NAME = BPCPQORG.CHN_NM;
        TDCQYBTF.RMK = TDCPPRTF.RMK;
        TDCQYBTF.BV_NO = TDCPPRTF.BV_NO;
        TDCQYBTF.AC_NAME = TDCPPRTF.AC_NAME;
        TDCQYBTF.CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
        TDCQYBTF.CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
        B900_OUTPUT_QYBTF_INF();
        if (pgmRtn) return;
        TDRYBTP.KEY.AC_NO = TDCPPRTF.AC;
        T000_STARTBR_YBTP_CHG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "RETURN YBTP CHG DB FLG");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        TDCQYBT.CNT = 0;
        DATA = new TDCQYBT_DATA();
        TDCQYBT.DATA.add(DATA);
        CEP.TRC(SCCGWA, WS_AC_SEQ);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_YBT_PRT_OVER != 'Y' 
            && TDRYBTP.PSBK_POS <= TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_SEQ);
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCPPRTF.AC;
            CICQACAC.DATA.AGR_SEQ = TDRYBTP.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_SMST();
            if (pgmRtn) return;
            R000_PRINT_YBT_CHG();
            if (pgmRtn) return;
            T000_READNEXT_YBTP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "CONTROL111");
            CEP.TRC(SCCGWA, TDCQYBT.CNT);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE);
            WS_PRINT_CNT = TDCQYBT.CNT;
            if (TDRYBTP.PRT_STS == '1' 
                || TDRYBTP.PRT_STS == '3') {
                WS_PRINT_CNT += 1;
            }
            if (TDRYBTP.PRT_STS == '5') {
                WS_PRINT_CNT += 2;
            }
            if (WS_PRINT_CNT > WS_CTRL_CNT) {
                WS_LINE = (short) (TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE % 2);
                WS_PRT_LINE = (short) ((TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE - WS_LINE) / 2);
                CEP.TRC(SCCGWA, WS_LINE);
                WS_YBT_PRT_OVER = 'Y';
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_PLS_BDZ, TDCPPRTF.RC_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (TDRYBTP.PSBK_POS >= TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            if (WS_MORE_RCD == 'Y') {
                TDCQYBT.PRT_FLG = '2';
            }
        } else {
            if (WS_PRINT_CNT > WS_CTRL_CNT 
                && WS_MORE_RCD == 'Y') {
                TDCQYBT.PRT_FLG = '1';
            }
        }
        T000_ENDBR_YBTP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRYBTP.PSBK_POS);
        CEP.TRC(SCCGWA, "HAVE ENDBR");
        if (TDCQYBT.CNT >= 0) {
            B900_OUTPUT_YBT_INF();
            if (pgmRtn) return;
        }
        B900_OUTPUT_MS_INF();
        if (pgmRtn) return;
        R000_CHANGE_PRT_FLG();
        if (pgmRtn) return;
    }
    public void B300_PRINT_PB() throws IOException,SQLException,Exception {
        TDCQPB.CNT = 0;
        DATA = new TDCQPB_DATA();
        TDCQPB.DATA.add(DATA);
        WS_QPB_FLG = 'N';
        TDRCDI.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRSMST.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        T000_READ_UPDATE_CDI();
        if (pgmRtn) return;
        T000_READ_UPDATE_SMST();
        if (pgmRtn) return;
        T000_STARTBR_PBP_PRT();
        if (pgmRtn) return;
        T000_READNEXT_PBP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_UNPRT_INF, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && TDRCDI.PSBK_POS < TDCUPARM.PARM_DATA.YBT_LINE_LMT 
            && WS_QPB_FLG != 'Y') {
            if (TDCQPB.CNT == WS_CTRL_CNT) {
                WS_QPB_FLG = 'Y';
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_PLS_BDZ, TDCPPRTF.RC_MSG);
            } else {
                R000_PRT_INF_TLZ();
                if (pgmRtn) return;
                T000_REWRITE_PBP();
                if (pgmRtn) return;
                T000_READNEXT_PBP();
                if (pgmRtn) return;
            }
        }
        if (TDRCDI.PSBK_POS >= TDCUPARM.PARM_DATA.YBT_LINE_LMT 
            && WS_MORE_RCD == 'Y') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_YBT_FULL_P_CHG, TDCPPRTF.RC_MSG);
        }
        T000_ENDBR_PBP();
        if (pgmRtn) return;
        T000_REWRITE_SMST();
        if (pgmRtn) return;
        T000_REWRITE_CDI();
        if (pgmRtn) return;
        if (TDCQPB.CNT > 0) {
            B900_OUTPUT_PB_INF();
            if (pgmRtn) return;
        }
    }
    public void B400_REPLACE_PB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDCQPB.CNT = 0;
        DATA = new TDCQPB_DATA();
        TDCQPB.DATA.add(DATA);
        WS_QPB_FLG = 'N';
        TDRCDI.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRSMST.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        TDRBVT.BV_NO = TDCPPRTF.BV_NO;
        T000_READ_UPDATE_CDI();
        if (pgmRtn) return;
        T000_READ_UPDATE_SMST();
        if (pgmRtn) return;
        T000_READ_BVT();
        if (pgmRtn) return;
        TDRSMST.PBAL = 0;
        TDRCDI.PSBK_POS = 0;
        WS_OPEN_BR = TDRSMST.OWNER_BR;
        B400_PRINT_PSBK_TITLE();
        if (pgmRtn) return;
        TDRPBP.KEY.AC_NO = TDCPPRTF.AC;
        T000_STARTBR_PBP_REP();
        if (pgmRtn) return;
        T000_READNEXT_PBP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && TDRCDI.PSBK_POS < TDCUPARM.PARM_DATA.YBT_LINE_LMT 
            && WS_QPB_FLG != 'Y') {
            if (TDCQPB.CNT == WS_CTRL_CNT) {
                WS_QPB_FLG = 'Y';
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_PLS_BDZ, TDCPPRTF.RC_MSG);
            } else {
                R000_PRT_INF_TLZ();
                if (pgmRtn) return;
                T000_REWRITE_PBP();
                if (pgmRtn) return;
                T000_READNEXT_PBP();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_PBP();
        if (pgmRtn) return;
        T000_REWRITE_SMST();
        if (pgmRtn) return;
        T000_REWRITE_CDI();
        if (pgmRtn) return;
        if (TDCQPB.CNT > 0) {
            B900_OUTPUT_PB_INF();
            if (pgmRtn) return;
        }
        B900_OUTPUT_MS_INF();
        if (pgmRtn) return;
    }
    public void B400_PRINT_PSBK_TITLE() throws IOException,SQLException,Exception {
        R000_GET_BK_NAME();
        if (pgmRtn) return;
        TDCQPBF.AC = TDRSMST.AC_NO;
        TDCQPBF.OPEN_BR = TDRSMST.OWNER_BR;
        TDCQPBF.OPEN_DATE = TDRSMST.OPEN_DATE;
        TDCQPBF.BK = TDRSMST.OWNER_BK;
        TDCQPBF.CCY = TDRSMST.CCY;
        TDCQPBF.TERM = TDRSMST.TERM;
        CEP.TRC(SCCGWA, TDCPPRTF.RAT);
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (TDCPPRTF.RAT != 0 
            || (!TDRSMST.PRDAC_CD.equalsIgnoreCase("022") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("029"))) {
            TDCQPBF.INT_RAT = TDCPPRTF.RAT;
        } else {
            T000_READ_IREV();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
            TDCQPBF.INT_RAT = TDRIREV.CON_RATE;
        }
        TDCQPBF.VAL_DATE = TDRSMST.VAL_DATE;
        TDCQPBF.EXP_DATE = TDRSMST.EXP_DATE;
        TDCQPBF.BAL = TDRSMST.BAL;
        TDCQPBF.EXP_INT = TDRSMST.EXP_INT;
        TDCQPBF.DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCQPBF.CD_PERD = TDRCDI.CD_PERD;
        TDCQPBF.CD_AMT = TDRCDI.CD_AMT;
        TDCQPBF.BK_NAME = BPCPQORG.CHN_NM;
        TDCQPBF.BV_NO = TDCPPRTF.BV_NO;
        TDCQPBF.AC_NAME = TDCPPRTF.AC_NAME;
        TDCQPBF.PRDAC_CD = TDCPPRTF.PRDAC_CD;
        TDCQPBF.RMK = TDCPPRTF.RMK;
        B900_OUTPUT_PBF_INF();
        if (pgmRtn) return;
    }
    public void B500_PRINT_BV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRSMST);
        CEP.TRC(SCCGWA, TDCPPRTF.AC);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_TYP);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCPPRTF.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        if (CICACCU.DATA.CI_TYP != '1') {
            TDRBVT.KEY.AC_NO = TDCPPRTF.ACO_AC;
        } else {
            TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        }
        if (TDCPPRTF.ACO_AC.trim().length() == 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCPPRTF.AC;
            CICQACAC.DATA.AGR_SEQ = TDCPPRTF.AC_SEQ;
            CICQACAC.DATA.BV_NO = TDCPPRTF.BV_NO;
            if (CICACCU.DATA.CI_TYP == '1') {
                CICQACAC.DATA.BV_NO = " ";
            }
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            TDCPPRTF.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
        TDRSMST.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRCMST.KEY.AC_NO = TDCPPRTF.AC;
        T000_READ_TDCMST();
        if (pgmRtn) return;
        T000_READ_SMST();
        if (pgmRtn) return;
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        if (TDCPPRTF.RAT == 0 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("030"))) {
            T000_READ_IREV();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRSMST.EXP_INT);
        CEP.TRC(SCCGWA, "BEGIN TEST");
        T000_READ_BVT();
        if (pgmRtn) return;
        WS_OPEN_BR = TDRSMST.OWNER_BR;
        R000_GET_BK_NAME();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCQBV);
        TDCQBV.AC = TDCPPRTF.AC;
        if (TDCPPRTF.BV_TYP == '7') {
            TDCQBV.AC = TDCPPRTF.ACO_AC;
        }
        CEP.TRC(SCCGWA, TDCQBV.AC);
        if (TDRBVT.BV_NO.trim().length() > 0) {
            TDCQBV.BV_NO = TDRBVT.BV_NO;
        } else {
            TDCQBV.BV_NO = TDCPPRTF.BV_NO;
        }
        TDCQBV.VAL_DATE = TDRSMST.VAL_DATE;
        TDCQBV.EXP_DATE = TDRSMST.EXP_DATE;
        TDCQBV.AC_NAME = TDCPPRTF.AC_NAME;
        TDCQBV.BK_NAME = BPCPQORG.CHN_NM;
        TDCQBV.OPEN_DATE = TDRSMST.OPEN_DATE;
        TDCQBV.OPEN_TLR = TDRSMST.CRT_TLR;
        TDCQBV.OPEN_BR = TDRSMST.OWNER_BR;
        TDCQBV.BAL = TDRSMST.BAL;
        TDCQBV.EXP_INT = TDRSMST.EXP_INT;
        TDCQBV.CCY = TDRSMST.CCY;
        TDCQBV.CCY_TYP = TDRSMST.CCY_TYP;
        TDCQBV.DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCQBV.TERM = TDRSMST.TERM;
        CEP.TRC(SCCGWA, "END SMST-TERM");
        CEP.TRC(SCCGWA, TDCPPRTF.RAT);
        if (TDCPPRTF.RAT != 0 
            || (DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("030"))) {
            TDCQBV.INT_RAT = TDCPPRTF.RAT;
        } else {
            TDCQBV.INT_RAT = TDRIREV.CON_RATE;
        }
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        CEP.TRC(SCCGWA, TDCQBV.INT_RAT);
        TDCQBV.PRDAC_CD = TDRSMST.PRDAC_CD;
        TDCQBV.INSTR_MTH = TDRSMST.INSTR_MTH;
        TDCQBV.RMK = TDCPPRTF.RMK;
        CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            if (TDRSMST.VAL_DATE == 0 
                || TDRSMST.EXP_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT, TDCPPRTF.RC_MSG);
            }
            WS_DATE1 = TDRSMST.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES36);
            WS_DATE2 = TDRSMST.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES41);
            if (WS_DATE2 < WS_DATE1) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT, TDCPPRTF.RC_MSG);
                Z_RET();
                if (pgmRtn) return;
            }
            REDEFINES47.WS_TERM_YY = 0;
            REDEFINES47.WS_TERM_MM = (short) (( REDEFINES41.WS_DATE2_YY - REDEFINES36.WS_DATE1_YY ) * 12 + REDEFINES41.WS_DATE2_MM - REDEFINES36.WS_DATE1_MM);
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = WS_DATE2;
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            if (REDEFINES41.WS_DATE2_DD < REDEFINES36.WS_DATE1_DD 
                && REDEFINES41.WS_DATE2_DD < SCCCKDT.MTH_DAYS) {
                REDEFINES47.WS_TERM_MM = (short) (REDEFINES47.WS_TERM_MM - 1);
            }
            if (REDEFINES47.WS_TERM_MM != 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE1;
                SCCCLDT.MTHS = REDEFINES47.WS_TERM_MM;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE3 = SCCCLDT.DATE2;
            } else {
                WS_DATE3 = WS_DATE1;
            }
            REDEFINES47.WS_TERM_DD = 0;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES47);
            WS_TERM_DT = Integer.parseInt(JIBS_tmp_str[0]);
            if (WS_DATE3 != WS_DATE2) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE3;
                SCCCLDT.DATE2 = WS_DATE2;
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                REDEFINES47.WS_TERM_DD = (short) SCCCLDT.DAYS;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES47);
                WS_TERM_DT = Integer.parseInt(JIBS_tmp_str[0]);
            }
            if (REDEFINES47.WS_TERM_MM >= 12) {
                REDEFINES47.WS_TERM_MM = (short) (REDEFINES47.WS_TERM_MM % 12);
                REDEFINES47.WS_TERM_YY = (short) ((REDEFINES47.WS_TERM_MM - REDEFINES47.WS_TERM_MM) / 12);
            } else {
                REDEFINES47.WS_TERM_YY = 0;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES47);
                WS_TERM_DT = Integer.parseInt(JIBS_tmp_str[0]);
            }
            TDCQBV.ACTI_NO.XY_DT = WS_TERM_DT;
        }
        CEP.TRC(SCCGWA, WS_TERM_DT);
        CEP.TRC(SCCGWA, TDCQBV.ACTI_NO.XY_DT);
        CEP.TRC(SCCGWA, TDCQBV.EXP_INT);
        CEP.TRC(SCCGWA, TDCQBV.INT_RAT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_BV_FMT;
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        CEP.TRC(SCCGWA, TDCQBV);
        SCCFMT.DATA_PTR = TDCQBV;
        SCCFMT.DATA_LEN = 601;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B800_REPRINT_YBTP_SAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRYBTP);
        TDRYBTP.KEY.AC_NO = TDCPPRTF.AC;
        T000_READ_YBTP_LAST();
        if (pgmRtn) return;
        WS_TMP_PSBK_POS = TDRYBTP.PSBK_POS;
        WS_LAST_POS = WS_TMP_PSBK_POS + TDCPPRTF.RP_LINES;
        WS_RP_STAR = TDCPPRTF.F_LINE;
        WS_RP_END = (short) (TDCPPRTF.F_LINE + TDCPPRTF.RP_LINES - 1);
        CEP.TRC(SCCGWA, TDCPPRTF.F_LINE);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_LINES);
        CEP.TRC(SCCGWA, WS_TMP_PSBK_POS);
        if (WS_RP_END > WS_TMP_PSBK_POS + 1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_MORE_PRTED, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_STARTBR_YBTP_REPRT1();
        if (pgmRtn) return;
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_RPT_DATA, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_UNPRT_INF, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        TDCQYBT.CNT = 0;
        DATA = new TDCQYBT_DATA();
        TDCQYBT.DATA.add(DATA);
        WS_AC_SEQ = 0;
        WS_QYBT_CNT = 0;
        IBS.init(SCCGWA, TDCUPARM);
        TDCUPARM.FUNC = 'I';
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (TDRYBTP.PRT_STS == '2'
                || TDRYBTP.PRT_STS == '3') {
                R000_PRT_OPN_INF();
                if (pgmRtn) return;
            } else if (TDRYBTP.PRT_STS == '4') {
                R000_PRT_OPN_INF();
                if (pgmRtn) return;
                R000_PRT_CLO_INF();
                if (pgmRtn) return;
            } else {
            }
            T000_READNEXT_YBTP();
            if (pgmRtn) return;
        }
        T000_ENDBR_YBTP();
        if (pgmRtn) return;
        B900_OUTPUT_YBT_INF();
        if (pgmRtn) return;
    }
    public void B800_REPRINT_YBTP_BOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRYBTP);
        TDRYBTP.KEY.AC_NO = TDCPPRTF.AC;
        WS_RP_STAR = TDCPPRTF.F_LINE;
        T000_READ_YBTP_FLINE();
        if (pgmRtn) return;
        T000_READ_YBTP_LAST();
        if (pgmRtn) return;
        WS_TMP_PSBK_POS = TDRYBTP.PSBK_POS;
        WS_LAST_POS = WS_TMP_PSBK_POS + TDCPPRTF.RP_LINES + 1;
        if (TDRYBTP.PRT_STS == '4') {
            WS_POS_J = (short) (TDRYBTP.PSBK_POS - TDCPPRTF.F_LINE + 1);
        } else {
            WS_POS_J = (short) (TDRYBTP.PSBK_POS - TDCPPRTF.F_LINE + 2);
        }
        WS_TMP_POS = (short) (( TDCPPRTF.RP_LINES + 1 ) / 2 * 2);
        WS_RP_END = (short) (TDCPPRTF.F_LINE + TDCPPRTF.RP_LINES - 1);
        CEP.TRC(SCCGWA, TDCPPRTF.F_LINE);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_LINES);
        CEP.TRC(SCCGWA, WS_TMP_PSBK_POS);
        if (WS_RP_END > WS_TMP_PSBK_POS + 1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_MORE_PRTED, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_LAST_POS > TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            CEP.TRC(SCCGWA, "NOT ALLOW");
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_MORE_DUE, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        TDRYBTP.PSBK_POS = (short) WS_TMP_PSBK_POS;
        T000_STARTBR_YBTP_REPRT2();
        if (pgmRtn) return;
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            TDRYBTP.PSBK_POS = (short) (TDRYBTP.PSBK_POS + WS_TMP_POS);
            T000_REWRITE_YBTP();
            if (pgmRtn) return;
            T000_READNEXT_YBTP();
            if (pgmRtn) return;
        }
        T000_ENDBR_YBTP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        if (TDCPPRTF.ACO_AC.trim().length() > 0) {
            TDRBVT.KEY.AC_NO = TDCPPRTF.ACO_AC;
        }
        T000_READU_BVT();
        if (pgmRtn) return;
        TDRBVT.PSBK_POS = TDRBVT.PSBK_POS + WS_TMP_POS;
        T000_UPDATE_BVT();
        if (pgmRtn) return;
        WS_RP_STAR = TDCPPRTF.F_LINE;
        WS_RP_END = (short) (TDCPPRTF.F_LINE + TDCPPRTF.RP_LINES - 1);
        CEP.TRC(SCCGWA, TDCPPRTF.F_LINE);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_LINES);
        CEP.TRC(SCCGWA, WS_TMP_PSBK_POS);
        if (WS_RP_END > WS_TMP_PSBK_POS + 1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_MORE_PRTED, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_STARTBR_YBTP_REPRT1();
        if (pgmRtn) return;
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_UNPRT_INF, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        TDCQYBT.CNT = 0;
        DATA = new TDCQYBT_DATA();
        TDCQYBT.DATA.add(DATA);
        WS_AC_SEQ = 0;
        WS_QYBT_CNT = 0;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            TDRYBTP.PSBK_POS = (short) (TDRYBTP.PSBK_POS + WS_POS_J);
            if (TDRYBTP.PRT_STS == '2'
                || TDRYBTP.PRT_STS == '3') {
                R000_PRT_OPN_INF();
                if (pgmRtn) return;
            } else if (TDRYBTP.PRT_STS == '4') {
                R000_PRT_OPN_INF();
                if (pgmRtn) return;
                R000_PRT_CLO_INF();
                if (pgmRtn) return;
            } else {
            }
            T000_REWRITE_YBTP();
            if (pgmRtn) return;
            T000_READNEXT_YBTP();
            if (pgmRtn) return;
        }
        B900_OUTPUT_YBT_INF();
        if (pgmRtn) return;
    }
    public void B800_REPRINT_YBTP_TOLINE() throws IOException,SQLException,Exception {
        TDRYBTP.KEY.AC_NO = TDCPPRTF.AC;
        WS_RP_STAR = TDCPPRTF.F_LINE;
        T000_READ_YBTP_FLINE();
        if (pgmRtn) return;
        T000_READ_YBTP_LAST();
        if (pgmRtn) return;
        WS_TMP_PSBK_POS = TDRYBTP.PSBK_POS;
        WS_LAST_POS = WS_TMP_PSBK_POS + TDCPPRTF.RP_LINES;
        if (TDRYBTP.PRT_STS != '4') {
            WS_TMP_PSBK_POS += 1;
        }
        if (TDCPPRTF.RP_TOLINE <= WS_TMP_PSBK_POS) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NEW_L_M_LARGE, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_POS_J = (short) (TDCPPRTF.RP_TOLINE - WS_TMP_PSBK_POS + TDCPPRTF.RP_LINES - 1);
        CEP.TRC(SCCGWA, WS_POS_J);
        WS_RP_END = (short) (TDCPPRTF.F_LINE + TDCPPRTF.RP_LINES - 1);
        CEP.TRC(SCCGWA, TDCPPRTF.F_LINE);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_LINES);
        CEP.TRC(SCCGWA, WS_TMP_PSBK_POS);
        if (WS_RP_END > WS_TMP_PSBK_POS + 1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_MORE_PRTED, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_LAST_POS > TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
            CEP.TRC(SCCGWA, "NOT ALLOW");
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_MORE_DUE, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        TDRYBTP.PSBK_POS = (short) WS_TMP_PSBK_POS;
        T000_STARTBR_YBTP_REPRT2();
        if (pgmRtn) return;
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_RPT_DATA, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            TDRYBTP.PSBK_POS = (short) (TDRYBTP.PSBK_POS + WS_POS_J);
            T000_REWRITE_YBTP();
            if (pgmRtn) return;
            T000_READNEXT_YBTP();
            if (pgmRtn) return;
        }
        T000_ENDBR_YBTP();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        T000_READU_BVT();
        if (pgmRtn) return;
        TDRBVT.PSBK_POS = TDRBVT.PSBK_POS + WS_POS_J;
        T000_UPDATE_BVT();
        if (pgmRtn) return;
        WS_RP_STAR = TDCPPRTF.F_LINE;
        WS_RP_END = (short) (TDCPPRTF.F_LINE + TDCPPRTF.RP_LINES - 1);
        CEP.TRC(SCCGWA, TDCPPRTF.F_LINE);
        CEP.TRC(SCCGWA, TDCPPRTF.RP_LINES);
        CEP.TRC(SCCGWA, WS_TMP_PSBK_POS);
        if (WS_RP_END > WS_TMP_PSBK_POS + 1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_MORE_PRTED, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_STARTBR_YBTP_REPRT1();
        if (pgmRtn) return;
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            TDRYBTP.PSBK_POS = (short) (TDRYBTP.PSBK_POS + WS_POS_J);
            if (TDRYBTP.PRT_STS == '2'
                || TDRYBTP.PRT_STS == '3') {
                R000_PRT_OPN_INF();
                if (pgmRtn) return;
            } else if (TDRYBTP.PRT_STS == '4') {
                R000_PRT_OPN_INF();
                if (pgmRtn) return;
                R000_PRT_CLO_INF();
                if (pgmRtn) return;
            } else {
            }
            T000_REWRITE_YBTP();
            if (pgmRtn) return;
            T000_READNEXT_YBTP();
            if (pgmRtn) return;
        }
        B900_OUTPUT_YBT_INF();
        if (pgmRtn) return;
    }
    public void B900_OUTPUT_YBT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_YBT_FMT;
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        TDCQYBT.MAC_NO = TDCPPRTF.AC;
        TDCQYBT.BV_NO = TDRBVT.BV_NO;
        SCCFMT.DATA_PTR = TDCQYBT;
        SCCFMT.DATA_LEN = 8106;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, TDCQYBT);
        CEP.TRC(SCCGWA, TDCQYBT.BV_NO);
        CEP.TRC(SCCGWA, TDCQYBT.CNT);
        for (WS_X = 1; WS_X <= TDCQYBT.CNT; WS_X += 1) {
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).PRT_NUM);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).PRT_LINE);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).SEQ_NO);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).TX_DATE);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).MMO);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).PRDAC_CD);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).CCY);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).CCY_TYPE);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).VAL_DATE);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).INT_TAX);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).TERM);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).AMT_X21);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).INT_RAT);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).INT);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).REF);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).FRG_TYPE);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).INSTR_MTH);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).TLR);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).PRT_NUM);
            CEP.TRC(SCCGWA, TDCQYBT.DATA.get(WS_X-1).XC_FLG);
            CEP.TRC(SCCGWA, "===============================");
        }
    }
    public void B900_OUTPUT_PB_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PB_FMT;
        TDCQPB.TD_AC = TDCPPRTF.AC;
        TDCQPB.BV_NO = TDRBVT.BV_NO;
        TDCQPB.EXP_DT = TDRSMST.EXP_DATE;
        SCCFMT.DATA_PTR = TDCQPB;
        SCCFMT.DATA_LEN = 12263;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, TDCQPB);
        CEP.TRC(SCCGWA, TDCQPB.TD_AC);
        CEP.TRC(SCCGWA, TDCQPB.BV_NO);
        CEP.TRC(SCCGWA, TDCQPB.CNT);
        for (WS_X = 1; WS_X <= TDCQPB.CNT; WS_X += 1) {
            CEP.TRC(SCCGWA, WS_X);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).PRT_LINE);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).DC_FLG);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).TX_DATE);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).MMO);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).INT_TAX);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).AMT);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).INT_RAT);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).BAL);
            CEP.TRC(SCCGWA, TDCQPB.DATA.get(WS_X-1).REF);
            CEP.TRC(SCCGWA, "============================");
        }
    }
    public void B900_OUTPUT_QYBTF_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_YBTF_FMT;
        SCCFMT.DATA_PTR = TDCQYBTF;
        SCCFMT.DATA_LEN = 518;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B900_OUTPUT_PBF_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PBF_FMT;
        SCCFMT.DATA_PTR = TDCQPBF;
        SCCFMT.DATA_LEN = 604;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, TDCQPBF);
    }
    public void B900_OUTPUT_MS_INF() throws IOException,SQLException,Exception {
        TDCQMS.AC = TDCPPRTF.AC;
        TDCQMS.BV_NO = TDCPPRTF.BV_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_MS_FMT;
        SCCFMT.DATA_PTR = TDCQMS;
        SCCFMT.DATA_LEN = 387;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_PRT_OPN_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRYBTP.AMT);
        if (TDRYBTP.KEY.AC_SEQ != WS_AC_SEQ) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDRYBTP.KEY.AC_NO;
            CICQACAC.DATA.AGR_SEQ = TDRYBTP.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            TDCPPRTF.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            TDRSMST.KEY.ACO_AC = TDCPPRTF.ACO_AC;
            T000_READ_SMST();
            if (pgmRtn) return;
            DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
            WS_AC_SEQ = TDRYBTP.KEY.AC_SEQ;
        }
        TDCQYBT.CNT += 1;
        DATA = new TDCQYBT_DATA();
        TDCQYBT.DATA.add(DATA);
        CEP.TRC(SCCGWA, TDCQYBT.CNT);
        CEP.TRC(SCCGWA, TDRYBTP.INT_RAT);
        WS_PRT_NUM = (short) (TDRYBTP.PSBK_POS + 1);
        TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_NUM = (short) ((WS_PRT_NUM - WS_PRT_NUM % 2) / 2);
        CEP.TRC(SCCGWA, WS_PRT_NUM);
        CEP.TRC(SCCGWA, TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_NUM);
        DATA.PRDAC_CD = TDRSMST.PRDAC_CD;
        DATA.CCY = TDRSMST.CCY;
        DATA.CCY_TYPE = TDRSMST.CCY_TYP;
        DATA.TERM = TDRSMST.TERM;
        DATA.INSTR_MTH = TDRSMST.INSTR_MTH;
        DATA.INT_TAX = TDRYBTP.TAX;
        DATA.PRT_LINE = TDRYBTP.PSBK_POS;
        DATA.SEQ_NO = TDRYBTP.KEY.AC_SEQ;
        DATA.VAL_DATE = TDRYBTP.VAL_DATE;
        if (TDRYBTP.FLR == null) TDRYBTP.FLR = "";
        JIBS_tmp_int = TDRYBTP.FLR.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) TDRYBTP.FLR += " ";
        if (TDRYBTP.FLR.substring(0, 2).equalsIgnoreCase("ZS")) {
            DATA.FRG_TYPE = 111;
        }
        DATA.EXP_DATE = TDRSMST.EXP_DATE;
        if (TDRYBTP.EXP_DATE != 0) {
            DATA.EXP_DATE = TDRYBTP.EXP_DATE;
        }
        df = new DecimalFormat(",,,,0.00");
        WS_AMT = df.format(TDRYBTP.BAL);
        CEP.TRC(SCCGWA, TDRYBTP.BAL);
        DATA.AMT_X21 = "" + WS_AMT;
        JIBS_tmp_int = DATA.AMT_X21.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) DATA.AMT_X21 = "0" + DATA.AMT_X21;
        DATA.INT = TDRYBTP.EXP_INT;
        CEP.TRC(SCCGWA, TDRYBTP.EXP_INT_RAT);
        DATA.INT_RAT = TDRYBTP.EXP_INT_RAT;
        DATA.TX_DATE = TDRYBTP.OPEN_DATE;
        DATA.REF = TDRYBTP.OPEN_TLR;
        DATA.TLR = TDRYBTP.OPEN_TLR;
        if (TDRYBTP.PRT_STS == '3') {
            DATA.MMO = TDRYBTP.CLO_MMO;
        } else {
            DATA.MMO = TDRYBTP.OPEN_MMO;
        }
        WS_LAST_PSBK_POS = TDRYBTP.PSBK_POS;
        if (TDRSMST.INSTR_MTH == '2' 
            || TDRSMST.INSTR_MTH == '3' 
            || TDRSMST.INSTR_MTH == '4' 
            || TDRSMST.INSTR_MTH == '5' 
            || TDRSMST.INSTR_MTH == '6') {
            DATA.XC_FLG = 'Y';
        } else {
            DATA.XC_FLG = ' ';
        }
    }
    public void R000_PRT_CLO_INF() throws IOException,SQLException,Exception {
        if (TDRYBTP.KEY.AC_SEQ != WS_AC_SEQ) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDRYBTP.KEY.AC_NO;
            CICQACAC.DATA.AGR_SEQ = TDRYBTP.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            TDCPPRTF.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            TDRSMST.KEY.ACO_AC = TDCPPRTF.ACO_AC;
            T000_READ_SMST();
            if (pgmRtn) return;
            DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
            WS_AC_SEQ = TDRYBTP.KEY.AC_SEQ;
            CEP.TRC(SCCGWA, TDRSMST.EXP_INT);
        }
        TDCQYBT.CNT += 1;
        DATA = new TDCQYBT_DATA();
        TDCQYBT.DATA.add(DATA);
        WS_PRT_NUM = (short) (TDRYBTP.PSBK_POS + 1);
        TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_NUM = (short) ((WS_PRT_NUM - WS_PRT_NUM % 2) / 2);
        CEP.TRC(SCCGWA, WS_PRT_NUM);
        CEP.TRC(SCCGWA, TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_NUM);
        TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE = (short) (TDRYBTP.PSBK_POS + 1);
        DATA.PRDAC_CD = TDRSMST.PRDAC_CD;
        CEP.TRC(SCCGWA, TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE);
        DATA.CCY = TDRSMST.CCY;
        DATA.CCY_TYPE = TDRSMST.CCY_TYP;
        DATA.TERM = TDRSMST.TERM;
        if (TDRYBTP.FLR == null) TDRYBTP.FLR = "";
        JIBS_tmp_int = TDRYBTP.FLR.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) TDRYBTP.FLR += " ";
        if (TDRYBTP.FLR.substring(0, 2).equalsIgnoreCase("ZS")) {
            DATA.FRG_TYPE = 111;
        }
        DATA.INSTR_MTH = ' ';
        DATA.VAL_DATE = TDRYBTP.VAL_DATE;
        DATA.EXP_DATE = TDRSMST.EXP_DATE;
        if (TDRYBTP.EXP_DATE != 0) {
            DATA.EXP_DATE = TDRYBTP.EXP_DATE;
        }
        DATA.INT_TAX = TDRYBTP.TAX;
        DATA.SEQ_NO = TDRYBTP.KEY.AC_SEQ;
        DATA.TX_DATE = TDRYBTP.CLO_DATE;
        DATA.REF = TDRYBTP.CLO_TLR;
        DATA.TLR = TDRYBTP.CLO_TLR;
        DATA.MMO = TDRYBTP.CLO_MMO;
        CEP.TRC(SCCGWA, TDRYBTP.AMT);
        df = new DecimalFormat(",,,,0.00");
        WS_AMT = df.format(TDRYBTP.AMT);
        CEP.TRC(SCCGWA, WS_AMT);
        DATA.AMT_X21 = "" + WS_AMT;
        JIBS_tmp_int = DATA.AMT_X21.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) DATA.AMT_X21 = "0" + DATA.AMT_X21;
        DATA.INT = TDRYBTP.INT;
        DATA.INT_TAX = TDRYBTP.TAX;
        CEP.TRC(SCCGWA, TDRYBTP.EXP_INT_RAT);
        CEP.TRC(SCCGWA, TDRYBTP.INT_RAT);
        DATA.INT_RAT = TDRYBTP.INT_RAT;
        WS_LAST_PSBK_POS = TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_LINE;
        DATA.XC_FLG = ' ';
    }
    public void R000_PRINT_YBT_CHG() throws IOException,SQLException,Exception {
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        TDRYBTP.KEY.AC_NO = TDCPPRTF.AC;
        TDCQYBT.CNT += 1;
        DATA = new TDCQYBT_DATA();
        TDCQYBT.DATA.add(DATA);
        WS_PRT_NUM = (short) (TDRYBTP.PSBK_POS + 1);
        TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_NUM = (short) ((WS_PRT_NUM - WS_PRT_NUM % 2) / 2);
        CEP.TRC(SCCGWA, WS_PRT_NUM);
        CEP.TRC(SCCGWA, TDCQYBT.DATA.get(TDCQYBT.CNT-1).PRT_NUM);
        DATA.PRDAC_CD = TDRSMST.PRDAC_CD;
        DATA.CCY = TDRSMST.CCY;
        DATA.CCY_TYPE = TDRSMST.CCY_TYP;
        if (TDRYBTP.FLR == null) TDRYBTP.FLR = "";
        JIBS_tmp_int = TDRYBTP.FLR.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) TDRYBTP.FLR += " ";
        if (TDRYBTP.FLR.substring(0, 2).equalsIgnoreCase("ZS")) {
            DATA.FRG_TYPE = 111;
        }
        DATA.TERM = TDRSMST.TERM;
        DATA.INSTR_MTH = TDRSMST.INSTR_MTH;
        DATA.PRT_LINE = TDRYBTP.PSBK_POS;
        DATA.SEQ_NO = TDRYBTP.KEY.AC_SEQ;
        DATA.VAL_DATE = TDRYBTP.VAL_DATE;
        DATA.INT_TAX = TDRYBTP.TAX;
        DATA.EXP_DATE = TDRSMST.EXP_DATE;
        if (TDRYBTP.EXP_DATE != 0) {
            DATA.EXP_DATE = TDRYBTP.EXP_DATE;
        }
        df = new DecimalFormat(",,,,0.00");
        WS_AMT = df.format(TDRYBTP.BAL);
        DATA.AMT_X21 = "" + WS_AMT;
        JIBS_tmp_int = DATA.AMT_X21.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) DATA.AMT_X21 = "0" + DATA.AMT_X21;
        DATA.INT_RAT = TDRYBTP.EXP_INT_RAT;
        DATA.TX_DATE = TDRYBTP.OPEN_DATE;
        DATA.REF = TDRYBTP.OPEN_TLR;
        DATA.TLR = TDRYBTP.OPEN_TLR;
        DATA.MMO = TDRYBTP.OPEN_MMO;
        if (TDRYBTP.PRT_STS == '1') {
            TDRYBTP.PRT_STS = '2';
            T000_REWRITE_YBTP();
            if (pgmRtn) return;
        }
        if (TDRSMST.INSTR_MTH == '2' 
            || TDRSMST.INSTR_MTH == '3' 
            || TDRSMST.INSTR_MTH == '4' 
            || TDRSMST.INSTR_MTH == '5' 
            || TDRSMST.INSTR_MTH == '6') {
            DATA.XC_FLG = 'Y';
        } else {
            DATA.XC_FLG = ' ';
        }
    }
    public void R000_CHANGE_PRT_FLG() throws IOException,SQLException,Exception {
        TDRYBTP.KEY.AC_NO = TDCPPRTF.AC;
        T000_STARTBR_YBTP_REP();
        if (pgmRtn) return;
        T000_READNEXT_YBTP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            TDRYBTP.PRT_STS = '4';
            T000_REWRITE_YBTP();
            if (pgmRtn) return;
            T000_READNEXT_YBTP();
            if (pgmRtn) return;
        }
        T000_ENDBR_YBTP();
        if (pgmRtn) return;
    }
    public void R000_PRT_INF_TLZ() throws IOException,SQLException,Exception {
        DATA = new TDCQPB_DATA();
        TDCQPB.DATA.add(DATA);
        TDCQPB.CNT = (short) (TDCQPB.CNT + 1);
        if (TDCPPRTF.RP_OPT == 'S') {
            WS_TMP_PBP_POS += 1;
        } else {
            TDRCDI.PSBK_POS = (short) (TDRCDI.PSBK_POS + 1);
            TDRPBP.PSBK_POS = TDRCDI.PSBK_POS;
        }
        if (TDRPBP.PROC_TYP == '6') {
            TDRSMST.PBAL = TDRSMST.BAL;
        } else {
            if (TDCPPRTF.RP_OPT != 'S') {
                TDRSMST.PBAL = TDRSMST.PBAL + TDRPBP.AMT + TDRPBP.INT - TDRPBP.TAX_AMT;
                if (TDRSMST.BAL < 0) {
                    TDRSMST.BAL = 0;
                }
            }
        }
        CEP.TRC(SCCGWA, "KING1");
        CEP.TRC(SCCGWA, TDRPBP.PROC_TYP);
        CEP.TRC(SCCGWA, TDRPBP.BAL);
        CEP.TRC(SCCGWA, WS_TMP_PBP_POS);
        CEP.TRC(SCCGWA, TDRCDI.PSBK_POS);
        CEP.TRC(SCCGWA, TDRPBP.AMT);
        CEP.TRC(SCCGWA, TDRPBP.PROC_DATE);
        CEP.TRC(SCCGWA, TDRPBP.PROC_TLR);
        CEP.TRC(SCCGWA, TDRPBP.PROC_MMO);
        CEP.TRC(SCCGWA, TDRPBP.RAT_INT);
        DATA.DC_FLG = TDRPBP.PROC_TYP;
        DATA.BAL = TDRPBP.BAL;
        if (TDCPPRTF.RP_OPT == 'S') {
            DATA.PRT_LINE = WS_TMP_PBP_POS;
        } else {
            DATA.PRT_LINE = TDRCDI.PSBK_POS;
        }
        DATA.AMT = TDRPBP.AMT;
        DATA.TX_DATE = TDRPBP.PROC_DATE;
        DATA.REF = TDRPBP.PROC_TLR;
        DATA.MMO = TDRPBP.PROC_MMO;
        DATA.INT_TAX = TDRPBP.TAX_AMT;
        DATA.INT_RAT = TDRPBP.RAT_INT;
        DATA.CCY = TDRSMST.CCY;
        DATA.TERM = TDRSMST.TERM;
        DATA.NUM = TDCQPB.DATA.get(TDCQPB.CNT-1).PRT_LINE;
        DATA.AC_TYP = TDRSMST.PRDAC_CD;
    }
    public void R000_PRT_PBP_RPT_TLINE() throws IOException,SQLException,Exception {
        DATA = new TDCQPB_DATA();
        TDCQPB.DATA.add(DATA);
        TDCQPB.CNT = (short) (TDCQPB.CNT + 1);
        WS_SUM_BAL = WS_SUM_BAL + TDRPBP.AMT;
        DATA.DC_FLG = TDRPBP.PROC_TYP;
        DATA.BAL = WS_SUM_BAL;
        DATA.PRT_LINE = TDRPBP.PSBK_POS;
        DATA.AMT = TDRPBP.AMT;
        DATA.TX_DATE = TDRPBP.PROC_DATE;
        DATA.REF = TDRPBP.PROC_TLR;
        DATA.MMO = TDRPBP.PROC_MMO;
        DATA.INT_TAX = 0;
        DATA.CCY = TDRSMST.CCY;
        DATA.TERM = TDRSMST.TERM;
        DATA.NUM = TDCQPB.DATA.get(TDCQPB.CNT-1).PRT_LINE;
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026"))) {
            DATA.AC_TYP = "TLZ";
        }
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("027")) {
            DATA.AC_TYP = "TCQ";
        }
    }
    public void R000_PRT_PBP_REPLACE() throws IOException,SQLException,Exception {
        DATA = new TDCQPB_DATA();
        TDCQPB.DATA.add(DATA);
        TDCQPB.CNT = (short) (TDCQPB.CNT + 1);
        TDRCDI.PSBK_POS = (short) (TDRCDI.PSBK_POS + 1);
        TDRPBP.PSBK_POS = TDRCDI.PSBK_POS;
        if (TDRPBP.PROC_TYP == '6') {
            TDRSMST.PBAL = TDRSMST.BAL;
        } else {
            TDRSMST.PBAL = TDRSMST.PBAL + TDRPBP.AMT + TDRPBP.INT - TDRPBP.TAX_AMT;
            if (TDRSMST.BAL < 0) {
                TDRSMST.BAL = 0;
            }
        }
        DATA.DC_FLG = TDRPBP.PROC_TYP;
        DATA.BAL = TDRSMST.PBAL;
        DATA.PRT_LINE = TDRCDI.PSBK_POS;
        DATA.AMT = TDRPBP.AMT;
        DATA.TX_DATE = TDRPBP.PROC_DATE;
        DATA.REF = TDRPBP.PROC_TLR;
        DATA.MMO = TDRPBP.PROC_MMO;
        DATA.INT_RAT = TDRPBP.RAT_INT;
        DATA.INT_TAX = 0;
        DATA.CCY = TDRSMST.CCY;
        DATA.TERM = TDRSMST.TERM;
        DATA.NUM = TDCQPB.DATA.get(TDCQPB.CNT-1).PRT_LINE;
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026"))) {
            DATA.AC_TYP = "TLZ";
        }
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("027")) {
            DATA.AC_TYP = "TCQ";
        }
    }
    public void R000_GET_BK_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_OPEN_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B000_PRT_YBT_TITTLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        IBS.init(SCCGWA, TDRBVT);
        TDRCMST.KEY.AC_NO = TDCPPRTF.AC;
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        T000_READ_BVT();
        if (pgmRtn) return;
        WS_OPEN_BR = TDRCMST.OWNER_BR;
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BR);
        R000_GET_BK_NAME();
        if (pgmRtn) return;
        TDCQYBTF.MAIN_AC = TDRCMST.KEY.AC_NO;
        TDCQYBTF.OPEN_BR = TDRCMST.OWNER_BK;
        TDCQYBTF.OPEN_DATE = TDRCMST.OPEN_DATE;
        TDCQYBTF.OPEN_TLR = TDRCMST.OPEN_TLR;
        TDCQYBTF.DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCQYBTF.BK_NAME = BPCPQORG.CHN_NM;
        TDCQYBTF.RMK = TDCPPRTF.RMK;
        TDCQYBTF.BV_NO = TDCPPRTF.BV_NO;
        TDCQYBTF.AC_NAME = TDCPPRTF.AC_NAME;
        TDCQYBTF.CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
        TDCQYBTF.CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
        B900_OUTPUT_QYBTF_INF();
        if (pgmRtn) return;
    }
    public void B000_PRT_PBP_TITTLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRCDI);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, TDRCMST);
        TDRCDI.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRSMST.KEY.ACO_AC = TDCPPRTF.ACO_AC;
        TDRBVT.KEY.AC_NO = TDCPPRTF.AC;
        TDRCMST.KEY.AC_NO = TDCPPRTF.AC;
        T000_READ_SMST();
        if (pgmRtn) return;
        T000_READ_TDCMST();
        if (pgmRtn) return;
        TDRBVT.BV_NO = TDCPPRTF.BV_NO;
        if (TDRCMST.STS == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("027") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            T000_READ_TDTCDI();
            if (pgmRtn) return;
        }
        T000_READ_BVT();
        if (pgmRtn) return;
        WS_OPEN_BR = TDRSMST.OWNER_BR;
        B400_PRINT_PSBK_TITLE();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_SUM_PBP_BAL() throws IOException,SQLException,Exception {
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        TDTPBP_RD.set = "WS-SUM-BAL=SUM(AMT)";
        TDTPBP_RD.where = "AC_NO = :TDRPBP.KEY.AC_NO "
            + "AND PROC_TYP < > '6' "
            + "AND PSBK_POS < :TDRPBP.PSBK_POS";
        IBS.GROUP(SCCGWA, TDRPBP, this, TDTPBP_RD);
    }
    public void T000_READ_UPDATE_CDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        TDTCDI_RD.upd = true;
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CDI() throws IOException,SQLException,Exception {
        TDRCDI.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.REWRITE(SCCGWA, TDRCDI, TDTCDI_RD);
    }
    public void T000_STARTBR_PBP_PRT() throws IOException,SQLException,Exception {
        TDRPBP.KEY.AC_NO = TDCPPRTF.AC;
        CEP.TRC(SCCGWA, TDRPBP.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRCDI.PSBK_POS);
        TDTPBP_BR.rp = new DBParm();
        TDTPBP_BR.rp.TableName = "TDTPBP";
        TDTPBP_BR.rp.upd = true;
        TDTPBP_BR.rp.where = "AC_NO = :TDRPBP.KEY.AC_NO "
            + "AND PROC_SEQ > :TDRCDI.PSBK_POS";
        TDTPBP_BR.rp.order = "PROC_DATE,PROC_SEQ";
        IBS.STARTBR(SCCGWA, TDRPBP, this, TDTPBP_BR);
    }
    public void T000_STARTBR_PBP_REPRT() throws IOException,SQLException,Exception {
        TDRPBP.KEY.AC_NO = TDCPPRTF.AC;
        TDTPBP_BR.rp = new DBParm();
        TDTPBP_BR.rp.TableName = "TDTPBP";
        TDTPBP_BR.rp.upd = true;
        TDTPBP_BR.rp.where = "AC_NO = :TDRPBP.KEY.AC_NO "
            + "AND PSBK_POS >= :WS_RP_STAR_P";
        TDTPBP_BR.rp.order = "PROC_DATE,PROC_SEQ";
        IBS.STARTBR(SCCGWA, TDRPBP, this, TDTPBP_BR);
    }
    public void T000_STARTBR_PBP_AFTER() throws IOException,SQLException,Exception {
        TDTPBP_BR.rp = new DBParm();
        TDTPBP_BR.rp.TableName = "TDTPBP";
        TDTPBP_BR.rp.upd = true;
        TDTPBP_BR.rp.where = "AC_NO = :TDRPBP.KEY.AC_NO "
            + "AND PSBK_POS > :WS_RP_END_P";
        TDTPBP_BR.rp.order = "PROC_DATE";
        IBS.STARTBR(SCCGWA, TDRPBP, this, TDTPBP_BR);
    }
    public void T000_STARTBR_PBP_REP() throws IOException,SQLException,Exception {
        TDRPBP.KEY.AC_NO = TDCPPRTF.AC;
        TDTPBP_BR.rp = new DBParm();
        TDTPBP_BR.rp.TableName = "TDTPBP";
        TDTPBP_BR.rp.upd = true;
        TDTPBP_BR.rp.where = "AC_NO = :TDRPBP.KEY.AC_NO";
        TDTPBP_BR.rp.order = "PROC_DATE,PROC_SEQ";
        IBS.STARTBR(SCCGWA, TDRPBP, this, TDTPBP_BR);
    }
    public void T000_READNEXT_PBP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPBP, this, TDTPBP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MORE_RCD = 'N';
        } else {
            WS_MORE_RCD = 'Y';
        }
    }
    public void T000_ENDBR_PBP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTPBP_BR);
    }
    public void T000_STARTBR_YBTP_PRT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '1' , '3' , '5' )";
        TDTYBTP_BR.rp.order = "PSBK_POS";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void T000_STARTBR_YBTP_REP() throws IOException,SQLException,Exception {
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '3' , '5' )";
        TDTYBTP_BR.rp.order = "PSBK_POS";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void T000_STARTBR_YBTP_CHG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN:T000-STARTBR-YBTP-CHG");
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '1' , '2' )";
        TDTYBTP_BR.rp.order = "AC_NO,PROC_SEQ,PRT_STS DESC";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void T000_READ_YBTP_LAST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        TDTYBTP_RD.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '2' , '3' , '4' )";
        TDTYBTP_RD.fst = true;
        TDTYBTP_RD.order = "PSBK_POS DESC";
        IBS.READ(SCCGWA, TDRYBTP, this, TDTYBTP_RD);
    }
    public void T000_READ_YBTP_FLINE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        CEP.TRC(SCCGWA, WS_RP_STAR);
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        TDTYBTP_RD.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PSBK_POS = :WS_RP_STAR";
        IBS.READ(SCCGWA, TDRYBTP, this, TDTYBTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_RPT_L_M_OPN_L, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_YBTP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
        CEP.TRC(SCCGWA, TDRYBTP);
        CEP.TRC(SCCGWA, TDRYBTP.AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MORE_RCD = 'N';
        } else {
            WS_MORE_RCD = 'Y';
        }
    }
    public void T000_ENDBR_YBTP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ENDBR");
        IBS.ENDBR(SCCGWA, TDTYBTP_BR);
    }
    public void T000_READ_UPDATE_YBTP() throws IOException,SQLException,Exception {
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        TDTYBTP_RD.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND AC_SEQ = :TDRYBTP.KEY.AC_SEQ "
            + "AND PART_NUM = :TDRYBTP.PART_NUM "
            + "AND PRT_STS <= '2'";
        TDTYBTP_RD.upd = true;
        IBS.READ(SCCGWA, TDRYBTP, this, TDTYBTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_YBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.REWRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void T000_REWRITE_PBP() throws IOException,SQLException,Exception {
        TDRPBP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        IBS.REWRITE(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_UPDATE_BVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READ_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE = :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE >= :TDRIREV.KEY.STR_DATE";
        TDTIREV_RD.fst = true;
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "OOOOOOOOOOOOOOO");
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_IREV_NOFND, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_SMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_BVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PBP_LAST() throws IOException,SQLException,Exception {
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        TDTPBP_RD.where = "AC_NO = :TDRPBP.KEY.AC_NO";
        TDTPBP_RD.fst = true;
        TDTPBP_RD.order = "PROC_SEQ DESC";
        IBS.READ(SCCGWA, TDRPBP, this, TDTPBP_RD);
    }
    public void T000_READ_BVT_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.fst = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READU_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T001_STARTBR_YBTP_PRT() throws IOException,SQLException,Exception {
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '2' , '4' ) "
            + "AND PSBK_POS >= :WS_RP_STAR";
        TDTYBTP_BR.rp.order = "PSBK_POS";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void T000_STARTBR_YBTP_REPRT1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        CEP.TRC(SCCGWA, WS_RP_STAR);
        CEP.TRC(SCCGWA, WS_RP_END);
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '2' , '3' , '4' ) "
            + "AND PSBK_POS >= :WS_RP_STAR "
            + "AND PSBK_POS <= :WS_RP_END";
        TDTYBTP_BR.rp.order = "PSBK_POS";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void T000_STARTBR_YBTP_REPRT2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRYBTP.PSBK_POS);
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PSBK_POS > :TDRYBTP.PSBK_POS "
            + "AND PRT_STS IN ( '1' , '3' , '5' )";
        TDTYBTP_BR.rp.order = "PSBK_POS";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void S000_CALL_TDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-TDPARM", TDCUPARM, true);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            JIBS_tmp_str[0] = "SC";
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPPRTF.RC_MSG);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPPRTF.RC_MSG);
            JIBS_tmp_str[1] = "" + SCCCKDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 3 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(3 + 4 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, WS_RC);
        if (WS_RC != 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "END S000-CALL-BPZPQORG NORMAL");
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void S000_CALL_DCCPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPPRTF.RC_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], SCCBINF);
    }
    public void T000_READ_BVT1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST, TDCPPRTF.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCPPRTF.RC_MSG.RC != 0) {
            CEP.TRC(SCCGWA, "TDCPPRTF=");
            CEP.TRC(SCCGWA, TDCPPRTF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
