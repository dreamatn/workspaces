package com.hisun.TD;

import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZBVCHG {
    DBParm TDTHBVT_RD;
    DBParm TDTYBTP_RD;
    DBParm TDTBVT_RD;
    DBParm TDTSTS_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTPLED_RD;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    brParm TDTSTS_BR = new brParm();
    brParm TDTYBTP_BR = new brParm();
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIAACR_RD;
    DBParm TDTPBP_RD;
    brParm DCTIACCY_BR = new brParm();
    String K_AP_MMO = "TD";
    String K_PRDP_TYP = "PRDP";
    String K_YBTF_FMT = "TD004";
    String K_MS_FMT = "TD006";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_STS_TBL = "0007";
    String WS_ERR_MSG = " ";
    TDZBVCHG_WS_RC_MSG WS_RC_MSG = new TDZBVCHG_WS_RC_MSG();
    int WS_END_DATE = 0;
    TDZBVCHG_WS_TABLES_INFO WS_TABLES_INFO = new TDZBVCHG_WS_TABLES_INFO();
    TDZBVCHG_WS_CHECK_INFO WS_CHECK_INFO = new TDZBVCHG_WS_CHECK_INFO();
    char WS_MAIN_AC_SIG = ' ';
    double WS_TD_TOT_BAL = 0;
    short WS_PSBK_SEQ = 0;
    short WS_NEW_POS = 0;
    String WS_TMP_LAST_TLR = " ";
    int WS_TMP_LAST_DATE = 0;
    char WS_TMP_DRAW_MTH = ' ';
    String WS_TMP_DRAW_INF = " ";
    char WS_TMP_CUS_PSW_FLG = ' ';
    char WS_TMP_CROS_DR_FLG = ' ';
    char WS_TMP_CROS_CR_FLG = ' ';
    String WS_TMP_STSW = " ";
    int WS_TMP_ISSU_DATE = 0;
    int WS_TMP_ISSU_BR = 0;
    String WS_TMP_ISSU_TLR = " ";
    int WS_TMP_LAST_BR = 0;
    char WS_PLOSS_FIG = ' ';
    char WS_TSTS_FIG = ' ';
    String WS_OLD_BVNO = " ";
    String WS_SUB_AC = " ";
    int WS_CHG_SEQ = 0;
    char WS_TABLE_FLG = ' ';
    char WS_DCTIACCY_FLG = ' ';
    char WS_STS_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCPLDPD BPCPLDPD = new BPCPLDPD();
    BPCFQFBV BPCFQFBV = new BPCFQFBV();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICMACR CICMACR = new CICMACR();
    CICACCU CICACCU = new CICACCU();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRYBTP TDRYBTP = new TDRYBTP();
    TDRPBP TDRPBP = new TDRPBP();
    TDCPPRTF TDCPPRTF = new TDCPPRTF();
    TDRSTS TDRSTS = new TDRSTS();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    DCRIACCY DCRIACCY = new DCRIACCY();
    BPCEX BPCEX = new BPCEX();
    DCCIACRU DCCIACRU = new DCCIACRU();
    TDCRPLED TDCRPLED = new TDCRPLED();
    TDCPLDT TDCPLDT = new TDCPLDT();
    TDRPLED TDRPLED = new TDRPLED();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCUPARM TDCUPARM = new TDCUPARM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDRIREV TDRIREV = new TDRIREV();
    TDRHBVT TDRHBVT = new TDRHBVT();
    CICQACAC CICQACAC = new CICQACAC();
    TDRCMST TDRCMST = new TDRCMST();
    CICSACAC CICSACAC = new CICSACAC();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    TDCPSWDR TDCPSWDR = new TDCPSWDR();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    TDCBVCHG TDCBVCHG;
    public void MP(SCCGWA SCCGWA, TDCBVCHG TDCBVCHG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCBVCHG = TDCBVCHG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZBVCHG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, DCCIMSTR);
        IBS.init(SCCGWA, TDRHBVT);
        CEP.TRC(SCCGWA, TDCBVCHG.CHG_TYP);
        CEP.TRC(SCCGWA, TDCBVCHG.LOS_NO);
        CEP.TRC(SCCGWA, TDCBVCHG.BV_CD);
        CEP.TRC(SCCGWA, TDCBVCHG.BV_TYP);
        CEP.TRC(SCCGWA, TDCBVCHG.BV_NO);
        CEP.TRC(SCCGWA, TDCBVCHG.AC_NO);
        CEP.TRC(SCCGWA, TDCBVCHG.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCBVCHG.PSW);
        CEP.TRC(SCCGWA, TDCBVCHG.ID_TYP);
        CEP.TRC(SCCGWA, TDCBVCHG.ID_NO);
        CEP.TRC(SCCGWA, TDCBVCHG.BV_NO_NEW);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCBVCHG.AC_NO;
        T000_READ_TDTCMST();
        CEP.TRC(SCCGWA, TDCBVCHG.PSWN);
        if (TDCBVCHG.PSWN.trim().length() > 0) {
            B001_CHANG_PSWD();
        }
        TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
        if (TDCBVCHG.BV_TYP != '1') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDRCMST.KEY.AC_NO;
            CICQACAC.DATA.BV_NO = TDCBVCHG.BV_NO;
            CICQACAC.DATA.AGR_SEQ = TDCBVCHG.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (TDCBVCHG.AC_SEQ == 0) {
                TDCBVCHG.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            }
            if (TDCBVCHG.BV_NO.trim().length() == 0) {
                TDCBVCHG.BV_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO;
            }
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (TDRSMST.BV_TYP != ' ' 
                && TDRCMST.BV_TYP == '0') {
                TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            }
        }
        B212_GET_AC_BV_INFO();
        B214_CHK_AC_BV_INFO();
        B230_BV_USE_PROC();
        B240_WRI_NFIN_HIS_PROC();
        if (TDCBVCHG.CHG_TYP == '1') {
            B252_REL_LOS_STS();
            B255_REL_RGTLOSS();
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B180_AGENT_INF_PORC();
        }
        if (TDCBVCHG.BV_TYP == '1') {
            if (TDCBVCHG.CHG_TYP == '3') {
                IBS.init(SCCGWA, TDCUPARM);
                TDCUPARM.FUNC = 'I';
                S000_CALL_TDZUPARM();
                CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.YBT_LINE_LMT);
                if (TDRBVT.PSBK_POS < TDCUPARM.PARM_DATA.YBT_LINE_LMT) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.M_YBT_PAGE_ALL_ERR);
                }
                T000_DELETE_TDTBVT();
                B250_EXC_NEW_YBTP_SMST();
                B250_WRT_TDTBVT_PROC();
            } else {
                T000_DELETE_TDTBVT();
                B250_REP_NEW_YBTP_SMST();
                B250_WRT_TDTBVT_PROC();
            }
        } else {
            if (TDCBVCHG.BV_TYP == '2') {
                T000_DELETE_TDTBVT();
                B250_WRT_TDTBVT_PROC();
                R000_UPDATE_CI_BV_NO();
            } else {
                B250_UPD_TDTBVT_PROC();
            }
        }
        if (TDCBVCHG.CHG_TYP == '1' 
            || TDCBVCHG.CHG_TYP == '2') {
            B250_CHG_TSTS_LOSS();
        }
        B251_UPD_IA_INFO_PROC();
        B260_PRINT_PB_PROC();
        if (TDCBVCHG.CHG_TYP != '5') {
            R000_WRITE_TDTHBVT();
        }
    }
    public void B001_CHANG_PSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPSWDR);
        TDCPSWDR.BV_CD = TDCBVCHG.BV_CD;
        TDCPSWDR.BV_TYP = TDCBVCHG.BV_TYP;
        TDCPSWDR.BV_NO = TDCBVCHG.BV_NO;
        TDCPSWDR.AC_NO = TDCBVCHG.AC_NO;
        TDCPSWDR.DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCPSWDR.ID_TYP = TDCBVCHG.ID_TYP;
        TDCPSWDR.ID_NO = TDCBVCHG.ID_NO;
        TDCPSWDR.PSW_N = TDCBVCHG.PSWN;
        TDCPSWDR.AC_SEQ = TDCBVCHG.AC_SEQ;
        S000_CALL_TDZPSWDR();
    }
    public void B250_CHG_TSTS_LOSS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDCBVCHG.AC_NO;
        T000_STARTBR_TDTSTS_2();
        T000_READNEXT_TDTSTS();
        CEP.TRC(SCCGWA, WS_TSTS_FIG);
        while (WS_TSTS_FIG != 'N') {
            B251_UPD_TDTSTS_PROC();
            T000_READNEXT_TDTSTS();
        }
    }
    public void B250_EXC_NEW_YBTP_SMST() throws IOException,SQLException,Exception {
        TDRYBTP.KEY.AC_NO = TDCBVCHG.AC_NO;
        T000_STARTBR_TDTYBTP();
        T000_READNEXT_TDTYBTP();
        WS_NEW_POS = 0;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            T000_DELETE_TDTYBTP();
            if (WS_NEW_POS > 0) {
                WS_NEW_POS = (short) (WS_NEW_POS + 2);
            } else {
                WS_NEW_POS = 1;
            }
            TDRYBTP.PSBK_POS = WS_NEW_POS;
            T000_WRITE_TDTYBTP();
            T000_READNEXT_TDTYBTP();
        }
        T000_ENDBR_TDTYBTP();
    }
    public void R000_WRITE_TDTHBVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRHBVT);
        TDRHBVT.KEY.NEW_BV_CD = TDCBVCHG.BV_CD;
        TDRHBVT.KEY.NEW_BV_TYP = TDCBVCHG.BV_TYP;
        TDRHBVT.KEY.NEW_BV_NO = TDCBVCHG.BV_NO;
        TDRHBVT.KEY.NAC = TDCBVCHG.AC_NO;
        TDRHBVT.KEY.NAC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        T000_READ_A_TDTHBVT();
        WS_CHG_SEQ = TDRHBVT.KEY.CHG_SEQ;
        CEP.TRC(SCCGWA, WS_CHG_SEQ);
        WS_CHG_SEQ += 1;
        IBS.init(SCCGWA, TDRHBVT);
        TDRHBVT.KEY.NAC = TDCBVCHG.AC_NO;
        TDRHBVT.OAC = TDCBVCHG.AC_NO;
        TDRHBVT.KEY.NAC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        TDRHBVT.OAC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        TDRHBVT.KEY.NEW_BV_TYP = TDCBVCHG.BV_TYP;
        TDRHBVT.OLD_BV_TYP = TDCBVCHG.BV_TYP;
        TDRHBVT.KEY.NEW_BV_NO = TDCBVCHG.BV_NO_NEW;
        TDRHBVT.OLD_BV_NO = TDCBVCHG.BV_NO;
        TDRHBVT.KEY.NEW_BV_CD = TDCBVCHG.BV_CD;
        TDRHBVT.OLD_BV_CD = TDCBVCHG.BV_CD;
        CEP.TRC(SCCGWA, TDCBVCHG.RC_MSG.AP_MMO);
        if (TDCBVCHG.RC_MSG.AP_MMO.equalsIgnoreCase("04")) {
            TDRHBVT.CHA_RMK = '4';
        } else {
            TDRHBVT.CHA_RMK = TDCBVCHG.CHG_TYP;
        }
        TDRHBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRHBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRHBVT.KEY.CHG_SEQ = WS_CHG_SEQ;
        T000_WRITE_TDTHBVT();
    }
    public void T000_READ_A_TDTHBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        TDTHBVT_RD.where = "NEW_BV_CD = :TDRHBVT.KEY.NEW_BV_CD "
            + "AND NEW_BV_TYP = :TDRHBVT.KEY.NEW_BV_TYP "
            + "AND NEW_BV_NO = :TDRHBVT.KEY.NEW_BV_NO "
            + "AND NAC = :TDRHBVT.KEY.NAC "
            + "AND NAC_SEQ = :TDRHBVT.KEY.NAC_SEQ";
        TDTHBVT_RD.fst = true;
        TDTHBVT_RD.order = "CHG_SEQ DESC";
        IBS.READ(SCCGWA, TDRHBVT, this, TDTHBVT_RD);
        CEP.TRC(SCCGWA, "END READ");
    }
    public void T000_DELETE_TDTYBTP() throws IOException,SQLException,Exception {
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.DELETE(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void T000_DELETE_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.DELETE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_DELETE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.DELETE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_WRITE_TDTYBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.WRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void B250_REP_NEW_YBTP_SMST() throws IOException,SQLException,Exception {
        TDRYBTP.KEY.AC_NO = TDCBVCHG.AC_NO;
        T000_STARTBR_TDTYBTP_REP();
        T000_READNEXT_TDTYBTP();
        WS_NEW_POS = 0;
        CEP.TRC(SCCGWA, "BEGIN B250-REP-NEW-YBTP-SMST");
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            T000_DELETE_TDTYBTP();
            if (WS_NEW_POS > 0) {
                WS_NEW_POS = (short) (WS_NEW_POS + 2);
            } else {
                WS_NEW_POS = 1;
            }
            TDRYBTP.PRT_STS = '1';
            TDRYBTP.PSBK_POS = WS_NEW_POS;
            CEP.TRC(SCCGWA, TDRYBTP.PSBK_POS);
            T000_WRITE_TDTYBTP();
            T000_READNEXT_TDTYBTP();
        }
        T000_ENDBR_TDTYBTP();
    }
    public void R000_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        CEP.TRC(SCCGWA, TDCBVCHG.AC_NO);
        TDRSTS.KEY.AC_NO = TDCBVCHG.AC_NO;
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        if (!(!(TDRSMST.BV_TYP != ' ') 
            && TDRCMST.BV_TYP == '0')) {
            TDRSTS.KEY.AC_SEQ = 0;
        } else {
            TDRSTS.KEY.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        }
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_SEQ);
        T000_STARTBR_TDTSTS();
        if (WS_TABLE_FLG == 'Y' 
            && TDCBVCHG.CHG_TYP != '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (WS_TABLE_FLG == 'N' 
            && TDCBVCHG.CHG_TYP == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_NOT_LOSS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B212_GET_AC_BV_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCBVCHG.AC_NO);
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TOO0_READ_BV_LAST();
        if (TDCBVCHG.CHG_TYP == '2') {
            CEP.TRC(SCCGWA, TDRBVT.CRT_TLR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            CEP.TRC(SCCGWA, TDRBVT.CRT_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (!TDRBVT.CRT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                || TDRBVT.CRT_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MST_TOD_TRL;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
        if (TDCBVCHG.BV_TYP == '1' 
            || TDCBVCHG.BV_TYP == '2') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            WS_NEW_POS = (short) TDRBVT.PSBK_POS;
            WS_OLD_BVNO = TDRBVT.BV_NO;
            WS_CHECK_INFO.WS_CHECK_BVT_STSW = TDRBVT.STSW;
            WS_TMP_STSW = TDRBVT.STSW;
            WS_TMP_ISSU_DATE = TDRBVT.ISSU_DATE;
            WS_TMP_ISSU_BR = TDRBVT.ISSU_BR;
            WS_TMP_ISSU_TLR = TDRBVT.ISSU_TLR;
            WS_TMP_LAST_BR = TDRBVT.UPD_BR;
            WS_TMP_LAST_TLR = TDRBVT.UPD_TLT;
            WS_TMP_LAST_DATE = TDRBVT.UPD_DATE;
            CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_INF);
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCBVCHG.AC_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
        if (TDCBVCHG.BV_TYP == '7' 
            || TDCBVCHG.BV_TYP == '1') {
            R000_CHK_MAIN_AC_STS();
        }
        R000_CHK_INPUT_PROC();
        if (TDCBVCHG.BV_TYP != '1') {
            CEP.TRC(SCCGWA, TDCBVCHG.AC_NO);
            R000_CHK_SMST_AC_STS();
            CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
            CEP.TRC(SCCGWA, TDRSMST.STSW);
            CEP.TRC(SCCGWA, TDRSMST.AC_BK);
            CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
            WS_CHECK_INFO.WS_CHECK_AC_STS = TDRSMST.ACO_STS;
            WS_CHECK_INFO.WS_CHECK_AC_STSW = TDRSMST.STSW;
            WS_CHECK_INFO.WS_CHECK_PROC_NUM = TDRSMST.PROC_NUM;
            WS_CHECK_INFO.WS_CHECK_PRDAC_CD = TDRSMST.PRDAC_CD;
        }
        if (TDCBVCHG.BV_TYP != '1') {
            WS_CHECK_INFO.WS_CHECK_BVT_STSW = TDRBVT.STSW;
            WS_OLD_BVNO = TDRBVT.BV_NO;
            CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_INF);
        }
        if (TDCBVCHG.CHG_TYP == '1') {
            IBS.init(SCCGWA, TDRSTS);
            TDRSTS.KEY.AC_NO = TDCBVCHG.AC_NO;
            TDRSTS.KEY.TYPE = '2';
            TDRSTS.KEY.POS = 3;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
            if (!(!(TDRSMST.BV_TYP != ' ') 
                && TDRCMST.BV_TYP == '0')) {
                TDRSTS.KEY.AC_SEQ = 0;
            } else {
                TDRSTS.KEY.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            }
            TDRSTS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_UPDATE_TDTSTS();
        }
    }
    public void B214_CHK_AC_BV_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_AC_STS);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_AC_STSW);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_AC_BK);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_MTH);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_INF);
        if (TDCBVCHG.CHG_TYP == '2' 
            && TDCBVCHG.BV_TYP != '1') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PLED_N_EXC;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_FRZ_N_EXC;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
        if (!TDCBVCHG.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO) 
            && TDCBVCHG.CHG_TYP != '5') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (WS_CHECK_INFO.WS_CHECK_AC_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (WS_CHECK_INFO.WS_CHECK_AC_STS == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (TDCBVCHG.CHG_TYP == '1') {
            if (WS_CHECK_INFO.WS_CHECK_BVT_STSW == null) WS_CHECK_INFO.WS_CHECK_BVT_STSW = "";
            JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_BVT_STSW += " ";
            if (!WS_CHECK_INFO.WS_CHECK_BVT_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_NOT_LOSS;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (CICACCU.DATA.CI_TYP != '1') {
                R000_GET_END_DATE_LNDT();
                if (WS_END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_7DAYS_LIMIT;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            }
            R000_GET_END_DATE();
        }
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW == null) WS_CHECK_INFO.WS_CHECK_BVT_STSW = "";
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_BVT_STSW += " ";
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW == null) WS_CHECK_INFO.WS_CHECK_BVT_STSW = "";
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_BVT_STSW += " ";
        if ((TDCBVCHG.CHG_TYP == '2' 
            || TDCBVCHG.CHG_TYP == '3') 
            && (WS_CHECK_INFO.WS_CHECK_BVT_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CHECK_INFO.WS_CHECK_BVT_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        R000_CHECK_STS_INFO();
    }
    public void R000_CHECK_STS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_AP_MMO;
        BPCFCSTS.TBL_NO = K_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(30);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.STSW == null) CICACCU.DATA.STSW = "";
        JIBS_tmp_int = CICACCU.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICACCU.DATA.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 31 - 1) + CICACCU.DATA.STSW + BPCFCSTS.STATUS_WORD.substring(31 + 30 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 61 - 1) + TDRCMST.STSW + BPCFCSTS.STATUS_WORD.substring(61 + 32 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 93 - 1) + TDRSMST.STSW + BPCFCSTS.STATUS_WORD.substring(93 + 32 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 125 - 1) + TDRBVT.STSW + BPCFCSTS.STATUS_WORD.substring(125 + 32 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void R000_CHK_MAIN_AC_STS() throws IOException,SQLException,Exception {
        if (TDRCMST.STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (TDCBVCHG.CHG_TYP == '2') {
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                || TDRCMST.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HOLD_N_EXC);
            }
        }
    }
    public void R000_CHK_SMST_AC_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (TDRSMST.ACO_STS == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B220_GET_MINI_BV_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFQFBV);
        BPCFQFBV.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFQFBV.TYPE = '1';
        BPCFQFBV.BV_CODE = TDCBVCHG.BV_CD;
        S000_CALL_BPZFQFBV();
        CEP.TRC(SCCGWA, BPCFQFBV.BEG_NO);
        TDCBVCHG.BV_NO_NEW = BPCFQFBV.BEG_NO;
    }
    public void B230_BV_USE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = TDCBVCHG.BV_CD;
        BPCUBUSE.BEG_NO = TDCBVCHG.BV_NO_NEW;
        BPCUBUSE.END_NO = TDCBVCHG.BV_NO_NEW;
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
    }
    public void B240_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        if (TDCBVCHG.CHG_TYP == '1') {
            BPCPNHIS.INFO.TX_TYP_CD = "P105";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P120";
        }
        BPCPNHIS.INFO.TX_TOOL = TDCBVCHG.AC_NO;
        BPCPNHIS.INFO.AC = TDCBVCHG.AC_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.AC_SEQ = TDCBVCHG.AC_SEQ;
        BPCPNHIS.INFO.FMT_ID = "TDZBVCHG";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void B250_UPD_TDTBVT_PROC() throws IOException,SQLException,Exception {
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        TDRBVT.STSW = TDRBVT.STSW.substring(0, 2 - 1) + "00" + TDRBVT.STSW.substring(2 + 2 - 1);
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            && TDCBVCHG.CHG_TYP == '1') {
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            TDRBVT.STSW = TDRBVT.STSW.substring(0, 7 - 1) + "0" + TDRBVT.STSW.substring(7 + 1 - 1);
        }
        CEP.TRC(SCCGWA, "TESTSTSW");
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        TDRBVT.BV_NO = TDCBVCHG.BV_NO_NEW;
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_TDTBVT();
        if (TDCBVCHG.BV_TYP != '1') {
            R000_UPDATE_CI_BV_NO();
        }
        CEP.TRC(SCCGWA, TDRBVT.PSBK_POS);
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
    }
    public void R000_UPDATE_CI_BV_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        CICSACAC.DATA.BV_NO = TDRBVT.BV_NO;
        if (TDCBVCHG.BV_TYP == '3') {
            CICSACAC.DATA.NOSEQ_FLG = 'Y';
        }
        S000_CALL_CIZSACAC();
    }
    public void B250_PAGE_OLD_BVT() throws IOException,SQLException,Exception {
        TDRBVT.PSBK_POS = TDRBVT.PSBK_POS - WS_NEW_POS - 1;
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_TDTBVT();
    }
    public void B250_WRT_TDTBVT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCBVCHG.AC_NO);
        CEP.TRC(SCCGWA, WS_TMP_STSW);
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.PSBK_POS = WS_NEW_POS;
        TDRBVT.KEY.AC_NO = TDCBVCHG.AC_NO;
        TDRBVT.BV_NO = TDCBVCHG.BV_NO_NEW;
        TDRBVT.BV_CD = TDCBVCHG.BV_CD;
        if (TDCBVCHG.CHG_TYP == '1') {
            if (WS_TMP_STSW == null) WS_TMP_STSW = "";
            JIBS_tmp_int = WS_TMP_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) WS_TMP_STSW += " ";
            WS_TMP_STSW = WS_TMP_STSW.substring(0, 3 - 1) + "0" + WS_TMP_STSW.substring(3 + 1 - 1);
            if (WS_TMP_STSW == null) WS_TMP_STSW = "";
            JIBS_tmp_int = WS_TMP_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) WS_TMP_STSW += " ";
            WS_TMP_STSW = WS_TMP_STSW.substring(0, 7 - 1) + "0" + WS_TMP_STSW.substring(7 + 1 - 1);
        }
        if (WS_TMP_STSW == null) WS_TMP_STSW = "";
        JIBS_tmp_int = WS_TMP_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) WS_TMP_STSW += " ";
        WS_TMP_STSW = "0" + WS_TMP_STSW.substring(1);
        TDRBVT.STSW = WS_TMP_STSW;
        TDRBVT.ISSU_DATE = WS_TMP_ISSU_DATE;
        TDRBVT.ISSU_BR = WS_TMP_ISSU_BR;
        TDRBVT.ISSU_TLR = WS_TMP_ISSU_TLR;
        TDRBVT.UPD_BR = WS_TMP_LAST_BR;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_BVT();
    }
    public void T000_WRITE_BVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.WRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void B251_UPD_IA_INFO_PROC() throws IOException,SQLException,Exception {
    }
    public void B252_REL_LOS_STS() throws IOException,SQLException,Exception {
        if (TDRSTS.REQ_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && TDRSTS.REQ_BR != BPCPORUP.DATA_INFO.BBR) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_INPUT_ERR);
        }
        if (TDRSTS.REMARK == null) TDRSTS.REMARK = "";
        JIBS_tmp_int = TDRSTS.REMARK.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) TDRSTS.REMARK += " ";
        if (!TDRSTS.REMARK.substring(0, 20).equalsIgnoreCase(TDCBVCHG.LOS_NO)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
        B280_30_WRITE_BPTLOSS_PROC();
        TDRSTS.STS = '0';
        TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSTS.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.BUSI_NO = "                                   ";
        T000_UPDATE_TDTSTS();
    }
    public void B255_REL_RGTLOSS() throws IOException,SQLException,Exception {
    }
    public void B180_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICACCU.DATA.CI_NO;
        CICSAGEN.OUT_AC = TDCBVCHG.AC_NO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "3";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void B280_30_WRITE_BPTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRLOSS);
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.FUNC = 'U';
        BPCSLOSS.LOS_NO = TDRSTS.REMARK;
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.STS = '5';
        BPCSLOSS.NEW_BV_NO = TDCBVCHG.BV_NO_NEW;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            BPCSLOSS.RLOS_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSLOSS.RLOS_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSLOSS.RLOS_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            BPCSLOSS.RLOS_TELE = CICGAGA_AGENT_AREA.PHONE;
        }
        S000_CALL_BPZSLOSS();
    }
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B251_UPD_TDTSTS_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_TDTSTS();
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTSTS();
    }
    public void T000_WRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.WRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void B260_PRINT_PB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        CEP.TRC(SCCGWA, TDCBVCHG.BV_TYP);
        CEP.TRC(SCCGWA, WS_MAIN_AC_SIG);
        CEP.TRC(SCCGWA, TDCBVCHG.CHG_TYP);
        if (TDCBVCHG.BV_TYP == '2') {
            TDCPPRTF.OPT = '2';
        }
        if (TDCBVCHG.BV_TYP == '1') {
            TDCPPRTF.OPT = '2';
        }
        if (TDCBVCHG.BV_TYP == '3' 
            || TDCBVCHG.BV_TYP == '7') {
            TDCPPRTF.OPT = '3';
        }
        TDCPPRTF.AC = TDCBVCHG.AC_NO;
        TDCPPRTF.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDCPPRTF.PRDAC_CD = WS_CHECK_INFO.WS_CHECK_PRDAC_CD;
        TDCPPRTF.BV_CD = TDCBVCHG.BV_CD;
        TDCPPRTF.BV_TYP = TDCBVCHG.BV_TYP;
        TDCPPRTF.BV_NO = TDCBVCHG.BV_NO_NEW;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, TDCPPRTF.ACO_AC);
        if (CICACCU.DATA.CI_TYP == '1') {
            if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                TDCPPRTF.AC_NAME = CICACCU.DATA.CI_CNM;
            } else {
                if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                    TDCPPRTF.AC_NAME = CICACCU.DATA.CI_ENM;
                } else {
                    TDCPPRTF.AC_NAME = CICACCU.DATA.AC_CNM;
                }
            }
        } else {
            if (CICACCU.DATA.AC_CNM.trim().length() > 0 
                && !CICACCU.DATA.CI_CNM.equalsIgnoreCase(CICACCU.DATA.AC_CNM)) {
                TDCPPRTF.AC_NAME = CICACCU.DATA.AC_CNM;
            } else {
                TDCPPRTF.AC_NAME = CICACCU.DATA.CI_CNM;
            }
        }
        CEP.TRC(SCCGWA, TDCPPRTF.OPT);
        CEP.TRC(SCCGWA, TDCPPRTF.AC);
        CEP.TRC(SCCGWA, TDCPPRTF.PRDAC_CD);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_CD);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_TYP);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        S000_CALL_TDZPPRTF();
    }
    public void R000_GEN_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "E143";
        SCCHMPW.INP_DATA.AC_FLG = '0';
        SCCHMPW.INP_DATA.OLD_AC = TDCBVCHG.AC_NO;
        SCCHMPW.INP_DATA.NEW_AC = TDCBVCHG.AC_NO;
        SCCHMPW.INP_DATA.PIN_DA = TDCBVCHG.PSW;
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.OLD_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.PIN_DA);
        S000_CALL_SCZHMPW();
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
        TDCBVCHG.PSW = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW;
    }
    public void R000_GET_END_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        IBS.init(SCCGWA, TDCUPARM);
        SCCCLDT.DATE1 = TDRSTS.STR_DATE;
        TDCUPARM.FUNC = 'I';
        S000_CALL_TDZUPARM();
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.LOST_NEWUSE_DAYS);
        if (TDCUPARM.PARM_DATA.LOST_NEWUSE_DAYS == 5) {
            SCCCLDT.DAYS = TDCUPARM.PARM_DATA.LOST_NEWUSE_DAYS;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            WS_END_DATE = SCCCLDT.DATE2;
        } else {
            WS_END_DATE = TDRSTS.STR_DATE;
        }
    }
    public void R000_GET_END_DATE_LNDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-BUG1");
        IBS.init(SCCGWA, SCCCLDT);
        IBS.init(SCCGWA, TDCUPARM);
        SCCCLDT.DATE1 = TDRSTS.STR_DATE;
        TDCUPARM.FUNC = 'I';
        S000_CALL_TDZUPARM();
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.COM_LNDT);
        if (TDCUPARM.PARM_DATA.COM_LNDT == 5) {
            SCCCLDT.DAYS = TDCUPARM.PARM_DATA.COM_LNDT;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            WS_END_DATE = SCCCLDT.DATE2;
        } else {
            WS_END_DATE = TDRSTS.STR_DATE;
        }
    }
    public void S000_CALL_TDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-TDPARM", TDCUPARM);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFQFBV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-INQ-FST-BVNO", BPCFQFBV);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_TDZPPRTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PSBK-PRTF", TDCPPRTF);
        if (TDCPPRTF.RC_MSG.RC != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCPPRTF.RC_MSG);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_TDZRPLED() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-R-TDZRPLED", TDCRPLED);
        if (TDCRPLED.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCRPLED.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void R00_READ() throws IOException,SQLException,Exception {
        TDTPLED_RD = new DBParm();
        TDTPLED_RD.TableName = "TDTPLED";
        TDTPLED_RD.where = "AC = :TDCBVCHG.AC_NO "
            + "AND STS = :TDRPLED.STS";
        TDTPLED_RD.fst = true;
        IBS.READ(SCCGWA, TDRPLED, this, TDTPLED_RD);
    }
    public void B210_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        S000_CALL_DCCIMSTR();
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S00_LINK_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZIACRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-ACRU-PROC", DCCIACRU);
        if (DCCIACRU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIACRU.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_WRITE_TDTHBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDRHBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        IBS.WRITE(SCCGWA, TDRHBVT, TDTHBVT_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.where = "AC_NO = :TDRSTS.KEY.AC_NO "
            + "AND AC_SEQ = :TDRSTS.KEY.AC_SEQ "
            + "AND TYPE = '2' "
            + "AND STS = '1' "
            + "AND POS = '3'";
        TDTSTS_RD.fst = true;
        TDTSTS_RD.order = "STR_DATE DESC";
        IBS.READ(SCCGWA, TDRSTS, this, TDTSTS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        }
    }
    public void T000_STARTBR_TDTSTS_2() throws IOException,SQLException,Exception {
        TDTSTS_BR.rp = new DBParm();
        TDTSTS_BR.rp.TableName = "TDTSTS";
        TDTSTS_BR.rp.upd = true;
        TDTSTS_BR.rp.where = "AC_NO = :TDRSTS.KEY.AC_NO";
        IBS.STARTBR(SCCGWA, TDRSTS, this, TDTSTS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        }
    }
    public void T000_READ_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        CEP.TRC(SCCGWA, "END READU TDTBVT");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_PSBK_SEQ > 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSBK_N_MATCH;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
    }
    public void T000_READ_UPDATE_TDTBVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_PSBK_SEQ > 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSBK_N_MATCH;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
    }
    public void T000_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READ_UPDATE_TDTYBTP() throws IOException,SQLException,Exception {
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        TDTYBTP_RD.upd = true;
        IBS.READ(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void T000_STARTBR_TDTYBTP() throws IOException,SQLException,Exception {
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '1' , '2' )";
        TDTYBTP_BR.rp.order = "PROC_SEQ";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.upd = true;
        DCTIAACR_BR.rp.where = "VCH_NO = :DCRIAACR.VCH_NO "
            + "AND ACCR_FLG = :DCRIAACR.ACCR_FLG";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_STARTBR_TDTYBTP_REP() throws IOException,SQLException,Exception {
        TDTYBTP_BR.rp = new DBParm();
        TDTYBTP_BR.rp.TableName = "TDTYBTP";
        TDTYBTP_BR.rp.upd = true;
        TDTYBTP_BR.rp.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND PRT_STS IN ( '1' , '2' )";
        TDTYBTP_BR.rp.order = "PROC_SEQ";
        IBS.STARTBR(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void S000_CALL_TDZPSWDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PSWD-RES", TDCPSWDR);
    }
    public void T000_READUP_DCTIAACR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.upd = true;
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND SEQ = :DCRIAACR.KEY.SEQ";
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_READNEXT_TDTSTS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSTS, this, TDTSTS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TSTS_FIG = 'Y';
        } else {
            WS_TSTS_FIG = 'N';
        }
    }
    public void T000_READNEXT_TDTYBTP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRYBTP, this, TDTYBTP_BR);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_STARTBR_DCTIAACR_FIRST() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND VCH_NO = :DCRIAACR.VCH_NO "
            + "AND ACCR_FLG = '1'";
        DCTIAACR_RD.fst = true;
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void TOO0_READ_BV_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_UPDATE_TDTYBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.REWRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void T000_UPDATE_DCTIAACR() throws IOException,SQLException,Exception {
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        IBS.REWRITE(SCCGWA, DCRIAACR, DCTIAACR_RD);
    }
    public void T000_READ_UPDATE_TDTPBP() throws IOException,SQLException,Exception {
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        TDTPBP_RD.upd = true;
        IBS.READ(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_READ_UPDATE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.where = "AC_NO = :TDRSTS.KEY.AC_NO "
            + "AND AC_SEQ = :TDRSTS.KEY.AC_SEQ "
            + "AND TYPE = :TDRSTS.KEY.TYPE "
            + "AND POS = :TDRSTS.KEY.POS "
            + "AND STR_DATE <= :TDRSTS.STR_DATE";
        TDTSTS_RD.upd = true;
        IBS.READ(SCCGWA, TDRSTS, this, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_UPDATE_TDTSTS() throws IOException,SQLException,Exception {
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.REWRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_UPDATE_TDTPBP() throws IOException,SQLException,Exception {
        TDRPBP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPBP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        IBS.REWRITE(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_STARTBR_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_BR.rp = new DBParm();
        DCTIACCY_BR.rp.TableName = "DCTIACCY";
        DCTIACCY_BR.rp.where = "VIA_AC = :DCRIACCY.KEY.VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIACCY, this, DCTIACCY_BR);
    }
    public void T000_READNEXT_DCTIACCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIACCY, this, DCTIACCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTIACCY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTIACCY_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_DCTIACCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIACCY_BR);
    }
    public void T000_ENDBR_TDTYBTP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTYBTP_BR);
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCBVCHG.RC_MSG.RC_CD != 0) {
            CEP.TRC(SCCGWA, "TDCBVCHG=");
            CEP.TRC(SCCGWA, TDCBVCHG);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
