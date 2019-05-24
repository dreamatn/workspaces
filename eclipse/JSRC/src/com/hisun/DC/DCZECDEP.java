package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZECDEP {
    int JIBS_tmp_int;
    DBParm DCTDCICT_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTICERD_RD;
    brParm DCTDCICH_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_DCICH_CNT = 0;
    DCZECDEP_WS_CARD_AMT WS_CARD_AMT = new DCZECDEP_WS_CARD_AMT();
    int WS_TIME = 0;
    String WS_CARD_NO_TEMP = " ";
    char WS_TBL_FLAG = ' ';
    char WS_FIND_FLG = ' ';
    char WS_BACK_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRICERD DCRICERD = new DCRICERD();
    DCRDCICH DCRDCICH = new DCRDCICH();
    DCRDCICT DCRDCICT = new DCRDCICT();
    CICQACAC CICQACAC = new CICQACAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICQCHDC CICQCHDC = new CICQCHDC();
    int WS_AC_DATE = 0;
    String WS_CARD_NO = " ";
    short WS_DCICT_CCNT = 0;
    short WS_ICERD_CCNT = 0;
    int WS_START_DATE = 0;
    int WS_END_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    DCCECDEP DCCECDEP;
    public void MP(SCCGWA SCCGWA, DCCECDEP DCCECDEP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCECDEP = DCCECDEP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZECDEP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_CARD_AMT);
        WS_DCICT_CCNT = 0;
        WS_ICERD_CCNT = 0;
        WS_CARD_NO = DCCECDEP.CARD_NO;
        WS_CARD_AMT.WS_ALDAY_BAL = DCCECDEP.ALDAY_BAL;
        if (DCCECDEP.ICERD_CCNT.trim().length() == 0) WS_ICERD_CCNT = 0;
        else WS_ICERD_CCNT = Short.parseShort(DCCECDEP.ICERD_CCNT);
        WS_START_DATE = DCCECDEP.START_DATE;
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = WS_CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BACK_FLG = 'Y';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_BACK_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STS == 'C' 
            && WS_BACK_FLG == 'Y') {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = DCCECDEP.CARD_NO;
            CICQCHDC.DATA.O_ENTY_TYP = '2';
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQCHDC.DATA.N_AGR_NO);
            if (CICQCHDC.DATA.N_AGR_NO.trim().length() > 0) {
                WS_CARD_NO = CICQCHDC.DATA.N_AGR_NO;
            }
        }
        if (WS_CARD_AMT.WS_ALDAY_BAL > 0 
            && (WS_BACK_FLG != 'N')) {
            B200_AMT_CHANGE_PROCESS();
            if (pgmRtn) return;
            B210_WRITE_DCTDCICT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRICERD);
        DCRICERD.KEY.CARD_NO = DCCECDEP.CARD_NO;
        DCRICERD.KEY.SEQ = DCCECDEP.SEQ;
        T000_READUPD_DCTICERD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCRICERD.NEXT_PROCESS_STS = '1';
            DCRICERD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRICERD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTICERD();
            if (pgmRtn) return;
        }
    }
    public void B100_COMP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICH);
        WS_DCICH_CNT = 0;
        WS_CARD_AMT.WS_DCICH_AMT = 0;
        T000_STARTAR_DCTDCICH();
        if (pgmRtn) return;
        T000_READNEXT_DCTDCICH();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            WS_CARD_AMT.WS_DCICH_AMT = WS_CARD_AMT.WS_DCICH_AMT + DCRDCICH.TXN_AMT;
            WS_DCICH_CNT += 1;
            CEP.TRC(SCCGWA, WS_DCICH_CNT);
            CEP.TRC(SCCGWA, WS_CARD_AMT.WS_DCICH_AMT);
            T000_READNEXT_DCTDCICH();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTDCICH();
        if (pgmRtn) return;
        WS_CARD_AMT.WS_OFF_CONS_AMT = WS_CARD_AMT.WS_DCICH_AMT;
        CEP.TRC(SCCGWA, WS_CARD_AMT.WS_OFF_CONS_AMT);
        CEP.TRC(SCCGWA, WS_CARD_AMT.WS_LAST_BAL);
        CEP.TRC(SCCGWA, WS_CARD_AMT.WS_DEPO_AMT);
        CEP.TRC(SCCGWA, WS_CARD_AMT.WS_ALDAY_BAL);
        WS_CARD_AMT.WS_ABNOR_BAL = WS_CARD_AMT.WS_ALDAY_BAL - WS_CARD_AMT.WS_OFF_CONS_AMT;
        CEP.TRC(SCCGWA, WS_CARD_AMT.WS_ABNOR_BAL);
    }
    public void B200_AMT_CHANGE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NNN-Q");
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = WS_CARD_NO;
        DDCUDRAC.CCY = "156";
        DDCUDRAC.TX_AMT = WS_CARD_AMT.WS_ALDAY_BAL;
        DDCUDRAC.AID = "003";
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.OTHER_AC = WS_CARD_NO;
        DDCUDRAC.OTHER_CCY = "156";
        DDCUDRAC.OTHER_AMT = WS_CARD_AMT.WS_ALDAY_BAL;
        DDCUDRAC.TX_MMO = "A033";
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.SMS_FLG = 'N';
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "NNN-C");
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = WS_CARD_NO;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.TX_AMT = WS_CARD_AMT.WS_ALDAY_BAL;
        DDCUCRAC.TX_MMO = "A033";
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B210_WRITE_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCICT.CARD_NO = DCCECDEP.CARD_NO;
        DCRDCICT.TXN_TYP = '9';
        DCRDCICT.TXN_AMT = WS_CARD_AMT.WS_ALDAY_BAL;
        DCRDCICT.TXN_CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        DCRDCICT.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRDCICT.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.WRITE_CARD_STS = '0';
        DCRDCICT.TXN_STS = '0';
        DCRDCICT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCICT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
    }
    public void T000_READ_DCTDCICT_FR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TBL DCTDCICT");
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.where = "( CARD_NO = :WS_CARD_NO ) "
            + "AND ( TXN_TYP = '0' "
            + "OR TXN_TYP = '1' "
            + "OR TXN_TYP = '2' ) "
            + "AND ( TXN_STS = '0' ) "
            + "AND ( WRITE_CARD_STS = '1' )";
        DCTDCICT_RD.fst = true;
        DCTDCICT_RD.order = "COUNT_CNT DESC";
        IBS.READ(SCCGWA, DCRDCICT, this, DCTDCICT_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ TBL DCTCDDAT");
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READUPD_DCTICERD() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        DCTICERD_RD.upd = true;
        IBS.READ(SCCGWA, DCRICERD, DCTICERD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_STARTAR_DCTDCICH() throws IOException,SQLException,Exception {
        WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCTDCICH_BR.rp = new DBParm();
        DCTDCICH_BR.rp.TableName = "DCTDCICH";
        DCTDCICH_BR.rp.where = "CARD_NO = :WS_CARD_NO "
            + "AND APP_TXN_NUM <= :WS_ICERD_CCNT "
            + "AND CLEAR_DATE >= :WS_START_DATE "
            + "AND CLEAR_DATE <= :WS_END_DATE";
        DCTDCICH_BR.rp.order = "APP_TXN_NUM";
        IBS.STARTBR(SCCGWA, DCRDCICH, this, DCTDCICH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READNEXT_DCTDCICH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDCICH, this, DCTDCICH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_REWRITE_DCTICERD() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        IBS.REWRITE(SCCGWA, DCRICERD, DCTICERD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_WRITE_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRDCICT, DCTDCICT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        }
    }
    public void T000_ENDBR_DCTDCICH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDCICH_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        CIZQCHDC CIZQCHDC = new CIZQCHDC();
        CIZQCHDC.MP(SCCGWA, CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
        Z_RET();
        if (pgmRtn) return;
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
        CEP.TRC(SCCGWA, "DCTICERD READ NUM:");
        CEP.TRC(SCCGWA, WS_CNT);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
