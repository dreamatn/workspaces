package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPMIBH {
    String JIBS_tmp_str[] = new String[10];
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    int JIBS_tmp_int;
    Random random;
    DBParm AITHMIBH_RD;
    DBParm AITMIBH_RD;
    DBParm AITMIB_RD;
    brParm AITMIBH_BR = new brParm();
    boolean pgmRtn = false;
    short WK_DEFAULT_CNT = 5;
    char K_STS_OPEN = 'O';
    char WS_HASH_TYP = ' ';
    char WS_HASH_CTL = ' ';
    short WS_HASH_CNT = 0;
    short WS_NUM1 = 0;
    short WS_I = 0;
    long WS_NUM2 = 0;
    short WS_PART_NO = 0;
    String WS_DATE_TIME = " ";
    long WK_SEED = 0;
    int WK_TIME = 0;
    long WK_VALUE = 0;
    String WK_RAND = " ";
    double WS_SUM_BAL = 0;
    double WS_TMP_BAL = 0;
    char WS_MIBH_FLG = ' ';
    char WS_MIB_FLG = ' ';
    AIRMIBH AIRMIBH = new AIRMIBH();
    AIRMIB AIRMIB = new AIRMIB();
    AIRHMIBH AIRHMIBH = new AIRHMIBH();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICMIBH_AIRMIB AICMIBH_AIRMIB = new AICMIBH_AIRMIB();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCRMOGS BPCRMOGS = new BPCRMOGS();
    BPRORGS BPRORGS = new BPRORGS();
    AICRMIBH AICRMIBH = new AICRMIBH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCRCWAT SCRCWA;
    AICPMIBH AICPMIBH;
    public void MP(SCCGWA SCCGWA, AICPMIBH AICPMIBH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPMIBH = AICPMIBH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPMIBH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICPMIBH.FUNC == 'R') {
        } else if (AICPMIBH.FUNC == 'U') {
            B100_SET_PART_NO();
            if (pgmRtn) return;
            B200_UPDATE_MIB();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC ERROR");
        }
    }
    public void B100_SET_PART_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPMIBH.HASH_TYP);
        CEP.TRC(SCCGWA, AICPMIBH.HASH_CTL);
        CEP.TRC(SCCGWA, AICPMIBH.HASH_CNT);
        WS_HASH_TYP = AICPMIBH.HASH_TYP;
        WS_HASH_CTL = AICPMIBH.HASH_CTL;
        if (AICPMIBH.HASH_CNT != 0) {
            WS_HASH_CNT = AICPMIBH.HASH_CNT;
        } else {
            WS_HASH_CNT = WK_DEFAULT_CNT;
        }
        if (WS_HASH_TYP == 'S') {
            R000_GEN_SEQUENCE_PART();
            if (pgmRtn) return;
        } else {
            R000_GEN_RANDOM_PART();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_PART_NO);
    }
    public void B200_UPDATE_MIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMIBH);
        IBS.init(SCCGWA, AIRMIB);
        AIRMIBH.KEY.PART_NO = WS_PART_NO;
        AIRMIBH.KEY.GL_BOOK = AICPMIBH.GL_BOOK;
        AIRMIB.KEY.GL_BOOK = AICPMIBH.GL_BOOK;
        AIRMIBH.KEY.BR = AICPMIBH.BR;
        AIRMIB.KEY.BR = AICPMIBH.BR;
        AIRMIBH.KEY.ITM_NO = AICPMIBH.ITM;
        AIRMIB.KEY.ITM_NO = AICPMIBH.ITM;
        AIRMIBH.KEY.SEQ = AICPMIBH.SEQ;
        AIRMIB.KEY.SEQ = AICPMIBH.SEQ;
        AIRMIBH.KEY.CCY = AICPMIBH.CCY;
        AIRMIB.KEY.CCY = AICPMIBH.CCY;
        CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AICPMIBH.BR);
        CEP.TRC(SCCGWA, AICPMIBH.ITM);
        CEP.TRC(SCCGWA, AICPMIBH.SEQ);
        CEP.TRC(SCCGWA, AICPMIBH.CCY);
        CEP.TRC(SCCGWA, AICPMIBH.HASH_TYP);
        CEP.TRC(SCCGWA, AICPMIBH.HASH_CTL);
        CEP.TRC(SCCGWA, AICPMIBH.HASH_CNT);
        CEP.TRC(SCCGWA, AICPMIBH.SIGN_FLG);
        CEP.TRC(SCCGWA, AICPMIBH.TR_AMT);
        CEP.TRC(SCCGWA, AICPMIBH.CBAL);
        CEP.TRC(SCCGWA, AICPMIBH.AFTER_BAL);
        T100_READ_RECORD_PROC();
        if (pgmRtn) return;
        T110_READ_RECORD_PROC();
        if (pgmRtn) return;
        if (WS_MIBH_FLG == 'N') {
            B310_CHECK_PROC_MIB();
            if (pgmRtn) return;
            R000_TRANS_DATA();
            if (pgmRtn) return;
            R000_CAL_CBAL();
            if (pgmRtn) return;
            T200_CREATE_RECORD_PROC();
            if (pgmRtn) return;
            if (AIRMIBH.KEY.ITM_NO.equalsIgnoreCase("30010803")) {
                T000_CREATE_HMIBH_PROC();
                if (pgmRtn) return;
            }
            if (AIRMIB.CBAL != 0 
                || AIRMIB.LBAL != 0) {
                T400_READUPD_MIB_PROC();
                if (pgmRtn) return;
                AIRMIB.CBAL = 0;
                AIRMIB.LBAL = 0;
                AIRMIB.HOT_FLG = 'H';
                T500_UPDATE_MIB_PROC();
                if (pgmRtn) return;
            }
        } else {
            B310_CHECK_PROC_MIB();
            if (pgmRtn) return;
            T100_READUPD_RECORD_PROC();
            if (pgmRtn) return;
            R000_CAL_CBAL();
            if (pgmRtn) return;
            T300_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            if (AIRMIBH.KEY.ITM_NO.equalsIgnoreCase("30010803")) {
                T000_CREATE_HMIBH_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_CHECK_PROC_MIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPMIBH.SIGN_FLG);
        CEP.TRC(SCCGWA, AIRMIBH.BAL_RFLG);
        CEP.TRC(SCCGWA, AIRMIBH.BAL_DIR);
        if (AIRMIBH.BAL_RFLG == 'N') {
            if ((AIRMIBH.BAL_DIR == 'C' 
                && AICPMIBH.SIGN_FLG == 'D') 
                || (AIRMIBH.BAL_DIR == 'C' 
                && AICPMIBH.SIGN_FLG == 'C' 
                && AICPMIBH.TR_AMT < 0)) {
                R000_SUM_MIB_BAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_SUM_BAL);
                if (WS_SUM_BAL < 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IA_RED_NOT_ALLOW);
                }
            }
            if ((AIRMIBH.BAL_DIR == 'D' 
                && AICPMIBH.SIGN_FLG == 'C') 
                || (AIRMIBH.BAL_DIR == 'D' 
                && AICPMIBH.SIGN_FLG == 'D' 
                && AICPMIBH.TR_AMT < 0)) {
                R000_SUM_MIB_BAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_SUM_BAL);
                if (WS_SUM_BAL > 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IA_RED_NOT_ALLOW);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_PART_NO);
        AIRMIBH.KEY.PART_NO = WS_PART_NO;
        CEP.TRC(SCCGWA, AIRMIBH.KEY.PART_NO);
    }
    public void B310_CHECK_PROC_MIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPMIBH.SIGN_FLG);
        CEP.TRC(SCCGWA, AIRMIB.BAL_RFLG);
        CEP.TRC(SCCGWA, AIRMIB.BAL_DIR);
        if (AIRMIB.BAL_RFLG == 'N') {
            if ((AIRMIB.BAL_DIR == 'C' 
                && AICPMIBH.SIGN_FLG == 'D') 
                || (AIRMIB.BAL_DIR == 'C' 
                && AICPMIBH.SIGN_FLG == 'C' 
                && AICPMIBH.TR_AMT < 0)) {
                R000_SUM_MIB_BAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_SUM_BAL);
                if (WS_SUM_BAL < 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IA_RED_NOT_ALLOW);
                }
            }
            if ((AIRMIB.BAL_DIR == 'D' 
                && AICPMIBH.SIGN_FLG == 'C') 
                || (AIRMIB.BAL_DIR == 'D' 
                && AICPMIBH.SIGN_FLG == 'D' 
                && AICPMIBH.TR_AMT < 0)) {
                R000_SUM_MIB_BAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_SUM_BAL);
                if (WS_SUM_BAL > 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IA_RED_NOT_ALLOW);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_PART_NO);
        AIRMIBH.KEY.PART_NO = WS_PART_NO;
        CEP.TRC(SCCGWA, AIRMIBH.KEY.PART_NO);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICMIBH_AIRMIB);
        IBS.init(SCCGWA, AICMIBH_AIRMIBH);
        IBS.init(SCCGWA, AIRMIBH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRMIB);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICMIBH_AIRMIB);
        CEP.TRC(SCCGWA, AICMIBH_AIRMIB.MIB_KEY.MIB_GL_BOOK);
        CEP.TRC(SCCGWA, AICMIBH_AIRMIB.MIB_KEY.MIB_BR);
        CEP.TRC(SCCGWA, AICMIBH_AIRMIB.MIB_KEY.MIB_ITM_NO);
        CEP.TRC(SCCGWA, AICMIBH_AIRMIB.MIB_KEY.MIB_SEQ);
        CEP.TRC(SCCGWA, AICMIBH_AIRMIB.MIB_KEY.MIB_CCY);
        AICMIBH_AIRMIBH.MIBH_KEY.MIBH_PART_NO = WS_PART_NO;
        CEP.TRC(SCCGWA, AICMIBH_AIRMIBH.MIBH_KEY.MIBH_PART_NO);
        AICMIBH_AIRMIBH.MIBH_KEY.MIBH_GL_BOOK = AICMIBH_AIRMIB.MIB_KEY.MIB_GL_BOOK;
        AICMIBH_AIRMIBH.MIBH_KEY.MIBH_BR = AICMIBH_AIRMIB.MIB_KEY.MIB_BR;
        AICMIBH_AIRMIBH.MIBH_KEY.MIBH_ITM_NO = AICMIBH_AIRMIB.MIB_KEY.MIB_ITM_NO;
        AICMIBH_AIRMIBH.MIBH_KEY.MIBH_SEQ = AICMIBH_AIRMIB.MIB_KEY.MIB_SEQ;
        AICMIBH_AIRMIBH.MIBH_KEY.MIBH_CCY = AICMIBH_AIRMIB.MIB_KEY.MIB_CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICMIBH_AIRMIB.MIB_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICMIBH_AIRMIBH.MIBH_DATA);
        AICMIBH_AIRMIBH.MIBH_DATA.MIBH_LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICMIBH_AIRMIBH);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AIRMIBH);
    }
    public void R000_CAL_CBAL() throws IOException,SQLException,Exception {
        if (AIRMIBH.LAST_TX_DT < SCCGWA.COMM_AREA.AC_DATE) {
            AIRMIBH.LBAL = AIRMIBH.CBAL;
            AIRMIBH.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            if (AIRMIBH.LAST_TX_DT > SCCGWA.COMM_AREA.AC_DATE) {
                if (AICPMIBH.SIGN_FLG == 'D') {
                    AIRMIBH.LBAL = AIRMIBH.CBAL - AICPMIBH.TR_AMT;
                }
                if (AICPMIBH.SIGN_FLG == 'C') {
                    AIRMIBH.LBAL = AIRMIBH.CBAL + AICPMIBH.TR_AMT;
                }
            }
        }
        AIRMIBH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRMIBH.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, AIRMIBH.CBAL);
        if (AICPMIBH.SIGN_FLG == 'D') {
            AIRMIBH.CBAL = AIRMIBH.CBAL - AICPMIBH.TR_AMT;
        }
        if (AICPMIBH.SIGN_FLG == 'C') {
            AIRMIBH.CBAL = AIRMIBH.CBAL + AICPMIBH.TR_AMT;
        }
        CEP.TRC(SCCGWA, AIRMIBH.CBAL);
    }
    public void R000_SUM_MIB_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRMIBH);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
        AICRMIBH.FUNC = 'S';
        AICRMIBH.POINTER = AIRMIBH;
        AICRMIBH.REC_LEN = 637;
        S000_CALL_AIZRMIBH();
        if (pgmRtn) return;
        if (AICRMIBH.RETURN_INFO == 'F' 
            || AICRMIBH.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, AICRMIBH.LBAL_SUM);
            CEP.TRC(SCCGWA, AICRMIBH.CBAL_SUM);
            if (AICPMIBH.SIGN_FLG == 'D') {
                WS_SUM_BAL = AICRMIBH.CBAL_SUM - AICPMIBH.TR_AMT;
            }
            if (AICPMIBH.SIGN_FLG == 'C') {
                WS_SUM_BAL = AICRMIBH.CBAL_SUM + AICPMIBH.TR_AMT;
            }
            CEP.TRC(SCCGWA, WS_SUM_BAL);
        }
    }
    public void R000_GEN_SEQUENCE_PART() throws IOException,SQLException,Exception {
        WS_DATE_TIME = " ";
        WS_NUM1 = 0;
        WS_NUM2 = 0;
        WS_PART_NO = 0;
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DATE_TIME = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DATE_TIME);
        if (SCCGWA.COMM_AREA.VCH_NO == null) SCCGWA.COMM_AREA.VCH_NO = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.VCH_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.VCH_NO += " ";
        if (SCCGWA.COMM_AREA.VCH_NO.substring(9 - 1, 9 + 4 - 1).trim().length() == 0) WS_NUM1 = 0;
        else WS_NUM1 = Short.parseShort(SCCGWA.COMM_AREA.VCH_NO.substring(9 - 1, 9 + 4 - 1));
        WS_PART_NO = (short) (WS_NUM1 % WS_HASH_CNT);
        WS_NUM2 = (long) ((WS_NUM1 - WS_PART_NO) / WS_HASH_CNT);
    }
    public void R000_GEN_RANDOM_PART() throws IOException,SQLException,Exception {
        WS_PART_NO = 0;
        WS_PART_NO = WS_HASH_CNT;
        CEP.TRC(SCCGWA, WS_PART_NO);
        random = new Random();
        WS_PART_NO = random.nextInt(WS_PART_NO);
        CEP.TRC(SCCGWA, WS_PART_NO);
    }
    public void T000_CREATE_HMIBH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRHMIBH);
        AIRHMIBH.KEY.SET_NO = SCCGWA.COMM_AREA.VCH_NO;
        AIRHMIBH.KEY.PART_NO = AIRMIBH.KEY.PART_NO;
        AIRHMIBH.KEY.GL_BOOK = AIRMIBH.KEY.GL_BOOK;
        AIRHMIBH.KEY.BR = AIRMIBH.KEY.BR;
        AIRHMIBH.KEY.ITM_NO = AIRMIBH.KEY.ITM_NO;
        AIRHMIBH.KEY.SEQ = AIRMIBH.KEY.SEQ;
        AIRHMIBH.KEY.CCY = AIRMIBH.KEY.CCY;
        AIRHMIBH.AC_NO = AIRMIBH.AC_NO;
        AIRHMIBH.ENG_NM = AIRMIBH.ENG_NM;
        AIRHMIBH.CHS_NM = AIRMIBH.CHS_NM;
        AIRHMIBH.STS = AIRMIBH.STS;
        AIRHMIBH.MAX_SEQ = AIRMIBH.MAX_SEQ;
        AIRHMIBH.AC_TYP = AIRMIBH.AC_TYP;
        AIRHMIBH.CCY_LMT = AIRMIBH.CCY_LMT;
        AIRHMIBH.BAL_DIR = AIRMIBH.BAL_DIR;
        AIRHMIBH.BAL_RFLG = AIRMIBH.BAL_RFLG;
        AIRHMIBH.AMT_DIR = AIRMIBH.AMT_DIR;
        AIRHMIBH.DTL_FLG = AIRMIBH.DTL_FLG;
        AIRHMIBH.RVS_TYP = AIRMIBH.RVS_TYP;
        AIRHMIBH.RVS_KND = AIRMIBH.RVS_KND;
        AIRHMIBH.RVS_EXP = AIRMIBH.RVS_EXP;
        AIRHMIBH.RVS_UNIT = AIRMIBH.RVS_UNIT;
        AIRHMIBH.AC_EXP = AIRMIBH.AC_EXP;
        AIRHMIBH.MANUAL_FLG = AIRMIBH.MANUAL_FLG;
        AIRHMIBH.ONL_FLG = AIRMIBH.ONL_FLG;
        AIRHMIBH.CARD_FLG = AIRMIBH.CARD_FLG;
        AIRHMIBH.HOT_FLG = AIRMIBH.HOT_FLG;
        AIRHMIBH.DRLT_BAL = AIRMIBH.DRLT_BAL;
        AIRHMIBH.CRLT_BAL = AIRMIBH.CRLT_BAL;
        AIRHMIBH.BAL_CHK = AIRMIBH.BAL_CHK;
        AIRHMIBH.APP1 = AIRMIBH.APP1;
        AIRHMIBH.APP2 = AIRMIBH.APP2;
        AIRHMIBH.APP3 = AIRMIBH.APP3;
        AIRHMIBH.APP4 = AIRMIBH.APP4;
        AIRHMIBH.APP5 = AIRMIBH.APP5;
        AIRHMIBH.APP6 = AIRMIBH.APP6;
        AIRHMIBH.APP7 = AIRMIBH.APP7;
        AIRHMIBH.APP8 = AIRMIBH.APP8;
        AIRHMIBH.APP9 = AIRMIBH.APP9;
        AIRHMIBH.APP10 = AIRMIBH.APP10;
        AIRHMIBH.CLN_REN = AIRMIBH.CLN_REN;
        AIRHMIBH.OPEN_DATE = AIRMIBH.OPEN_DATE;
        AIRHMIBH.CLS_DATE = AIRMIBH.CLS_DATE;
        AIRHMIBH.OPEN_TLR = AIRMIBH.OPEN_TLR;
        AIRHMIBH.CLS_TLR = AIRMIBH.CLS_TLR;
        AIRHMIBH.LAST_TX_DT = AIRMIBH.LAST_TX_DT;
        AIRHMIBH.AMT = AICPMIBH.TR_AMT;
        AIRHMIBH.CBAL = AIRMIBH.CBAL;
        if (AICPMIBH.SIGN_FLG == 'D') {
            AIRHMIBH.DR_CR_FLG = "DR";
        }
        if (AICPMIBH.SIGN_FLG == 'C') {
            AIRHMIBH.DR_CR_FLG = "CR";
        }
        AIRHMIBH.LAST_UPDATE_TLR = AIRMIBH.LAST_UPDATE_TLR;
        AIRHMIBH.UPDTBL_DATE = AIRMIBH.UPDTBL_DATE;
        AIRHMIBH.TS = AIRMIBH.TS;
        AITHMIBH_RD = new DBParm();
        AITHMIBH_RD.TableName = "AITHMIBH";
        AITHMIBH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRHMIBH, AITHMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MIBH_FLG = 'P';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_ALREADY_EXIST, AICPMIBH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITHMIBH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T100_READ_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRMIBH.KEY.PART_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        AITMIBH_RD.eqWhere = "PART_NO,GL_BOOK,BR,ITM_NO,SEQ,CCY";
        IBS.READ(SCCGWA, AIRMIBH, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AIRMIBH.CBAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIBH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T100_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        AITMIBH_RD.upd = true;
        IBS.READ(SCCGWA, AIRMIBH, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIBH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T110_READ_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        IBS.READ(SCCGWA, AIRMIB, AITMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T200_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRMIBH.KEY.PART_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        AITMIBH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRMIBH, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MIBH_FLG = 'P';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_ALREADY_EXIST, AICPMIBH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T300_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRMIBH.KEY.PART_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
        CEP.TRC(SCCGWA, AIRMIBH.LBAL);
        CEP.TRC(SCCGWA, AIRMIBH.CBAL);
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        IBS.REWRITE(SCCGWA, AIRMIBH, AITMIBH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.PART_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
        CEP.TRC(SCCGWA, AIRMIBH.LBAL);
        CEP.TRC(SCCGWA, AIRMIBH.CBAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBH_FLG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T400_READUPD_MIB_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.upd = true;
        IBS.READ(SCCGWA, AIRMIB, AITMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T500_UPDATE_MIB_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        IBS.REWRITE(SCCGWA, AIRMIB, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIB_FLG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T400_STARTBR_AITMIBH() throws IOException,SQLException,Exception {
        AITMIBH_BR.rp = new DBParm();
        AITMIBH_BR.rp.TableName = "AITMIBH";
        AITMIBH_BR.rp.upd = true;
        AITMIBH_BR.rp.where = "GL_BOOK = :AIRMIBH.KEY.GL_BOOK "
            + "AND BR = :AIRMIBH.KEY.BR "
            + "AND ITM_NO = :AIRMIBH.KEY.ITM_NO "
            + "AND SEQ = :AIRMIBH.KEY.SEQ "
            + "AND CCY = :AIRMIBH.KEY.CCY";
        AITMIBH_BR.rp.order = "AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIBH, this, AITMIBH_BR);
    }
    public void T500_READNEXT_AITMIBH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMIBH, this, AITMIBH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIBH_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T600_ENDBR_AITMIBH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMIBH_BR);
    }
    public void S000_CALL_AIZRMIBH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIBH", AICRMIBH);
        CEP.TRC(SCCGWA, AICRMIBH.RC);
        CEP.TRC(SCCGWA, AICRMIBH.RC.RC_CODE);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICPMIBH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AITMIBH = ");
            CEP.TRC(SCCGWA, AIRMIBH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
