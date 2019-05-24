package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSERIN {
    DBParm DCTCDDAT_RD;
    DBParm DCTDCICE_RD;
    DBParm DCTDCICH_RD;
    brParm DCTDCICE_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT = "DC311";
    int MAX_COL = 99;
    int MAX_ROW = 50;
    int COL_CNT = 8;
    String HIS_REMARK = "OFFLINE E-CASH CONSUMPTION ERR MAINTENANCE";
    short PAGE_ROW = 25;
    DCZSERIN_WS_VARIABLES WS_VARIABLES = new DCZSERIN_WS_VARIABLES();
    DCZSERIN_WS_OUTPUT WS_OUTPUT = new DCZSERIN_WS_OUTPUT();
    DCZSERIN_WS_OUTPUT_REW WS_OUTPUT_REW = new DCZSERIN_WS_OUTPUT_REW();
    DCZSERIN_WS_DB_VARS WS_DB_VARS = new DCZSERIN_WS_DB_VARS();
    DCZSERIN_WS_CONDITION_FLAG WS_CONDITION_FLAG = new DCZSERIN_WS_CONDITION_FLAG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRDCICE DCRDCICE = new DCRDCICE();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICQCHDC CICQCHDC = new CICQCHDC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCUTRF DDCUTRF = new DDCUTRF();
    DCRDCICH DCRDCICH = new DCRDCICH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCS3310 DCCS3310;
    public void MP(SCCGWA SCCGWA, DCCS3310 DCCS3310) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS3310 = DCCS3310;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSERIN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUTPUT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCS3310.FUNC == 'Q') {
            B010_QUERY_PROC();
            if (pgmRtn) return;
        } else if (DCCS3310.FUNC == 'M') {
            B020_MODIFY_PROC();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROC() throws IOException,SQLException,Exception {
        if (DCCS3310.CLEAR_DATE == ' ') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_LI_DATE_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, DCCS3310.CLEAR_DATE);
        if (DCCS3310.CLEAR_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_LI_DATE_GR_AC_DATE);
        }
        if (DCCS3310.CARD_NO.trim().length() == 0) {
            IBS.init(SCCGWA, DCRDCICE);
            T000_STARTBR_DCTDCICE_DT();
            if (pgmRtn) return;
            T000_READNEXT_DCTDCICE();
            if (pgmRtn) return;
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
            while (WS_CONDITION_FLAG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                T000_READ_DCTDCICH();
                if (pgmRtn) return;
                B040_MOVE_DATA();
                if (pgmRtn) return;
                B040_01_02_OUTPUT();
                if (pgmRtn) return;
                T000_READNEXT_DCTDCICE();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTDCICE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, DCCS3310.CARD_NO);
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCS3310.CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (WS_CONDITION_FLAG.TBL_FLAG == 'N') {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_EXIST);
            }
            IBS.init(SCCGWA, DCRDCICE);
            T000_STARTBR_DCTDCICE_DT_NO();
            if (pgmRtn) return;
            T000_READNEXT_DCTDCICE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, WS_CONDITION_FLAG.TBL_FLAG);
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
            while (WS_CONDITION_FLAG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                T000_READ_DCTDCICH();
                if (pgmRtn) return;
                B040_MOVE_DATA();
                if (pgmRtn) return;
                B040_01_02_OUTPUT();
                if (pgmRtn) return;
                T000_READNEXT_DCTDCICE();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTDCICE();
            if (pgmRtn) return;
        }
    }
    public void B020_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICE);
        DCRDCICE.KEY.CLEAR_DATE = DCCS3310.CLEAR_DATE;
        DCRDCICE.KEY.BATCH_NO = DCCS3310.BATCH_NO;
        DCRDCICE.KEY.BATCH_SN = DCCS3310.BATCH_SN;
        CEP.TRC(SCCGWA, DCCS3310.CLEAR_DATE);
        CEP.TRC(SCCGWA, DCCS3310.BATCH_NO);
        CEP.TRC(SCCGWA, DCCS3310.BATCH_SN);
        T000_READ_DCTDCICE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRDCICE.LAST_STS);
        CEP.TRC(SCCGWA, DCRDCICE.CARD_NO);
        CEP.TRC(SCCGWA, DCRDCICE.TXN_AMT);
        if (WS_CONDITION_FLAG.TBL_FLAG == 'Y') {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCRDCICE.CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            WS_VARIABLES.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            if (DCRCDDAT.CARD_STS == 'C') {
                IBS.init(SCCGWA, CICQCHDC);
                CICQCHDC.DATA.O_AGR_NO = DCRDCICE.CARD_NO;
                CICQCHDC.DATA.O_ENTY_TYP = '2';
                CICQCHDC.FUNC = 'W';
                S000_CALL_CIZQCHDC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQCHDC.DATA.N_AGR_NO);
            }
            if (CICQCHDC.DATA.N_AGR_NO.trim().length() == 0 
                && DCRCDDAT.MOVE_FLG == 'Y') {
                WS_VARIABLES.CI_NO = "88888888";
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.CI_NO);
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = "0434521562123000101000012";
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUTRF);
            DDCUTRF.FR_AC = "0434521562123000101000012";
            DDCUTRF.FR_APP = "AI";
            DDCUTRF.FR_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
            if (CICQCHDC.DATA.N_AGR_NO.trim().length() == 0) {
                DDCUTRF.TO_AC = DCRDCICE.CARD_NO;
                DDCUTRF.RLT_AC = DCRDCICE.CARD_NO;
            } else {
                DDCUTRF.TO_AC = CICQCHDC.DATA.N_AGR_NO;
                DDCUTRF.RLT_AC = CICQCHDC.DATA.N_AGR_NO;
            }
            DDCUTRF.TO_APP = "DD";
            DDCUTRF.CI_NO = WS_VARIABLES.CI_NO;
            DDCUTRF.CCY = "156";
            DDCUTRF.CCY_TYPE = '1';
            DDCUTRF.TX_AMT = DCRDCICE.TXN_AMT;
            DDCUTRF.TX_MMO = "G001";
            DDCUTRF.RLT_CCY = "156";
            S000_CALL_DDZUTRF();
            if (pgmRtn) return;
            T000_REWRITE_DCTDCICE();
            if (pgmRtn) return;
            WS_OUTPUT_REW.OW_CLEAR_DATE = DCRDCICE.KEY.CLEAR_DATE;
            WS_OUTPUT_REW.OW_CARD_NO = DCRDCICE.CARD_NO;
            WS_OUTPUT_REW.OW_LAST_STS = DCRDCICE.LAST_STS;
            WS_OUTPUT_REW.OW_UPDTBL_DATE = DCRDCICE.UPDTBL_DATE;
            WS_OUTPUT_REW.OW_UPDTBL_TLR = DCRDCICE.UPDTBL_TLR;
            CEP.TRC(SCCGWA, WS_OUTPUT_REW.OW_CLEAR_DATE);
            CEP.TRC(SCCGWA, WS_OUTPUT_REW.OW_CARD_NO);
            CEP.TRC(SCCGWA, WS_OUTPUT_REW.OW_LAST_STS);
            CEP.TRC(SCCGWA, WS_OUTPUT_REW.OW_UPDTBL_DATE);
            CEP.TRC(SCCGWA, WS_OUTPUT_REW.OW_UPDTBL_TLR);
            B040_01_03_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCICE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B040_MOVE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.O_CLEAR_DATE = DCRDCICH.KEY.CLEAR_DATE;
        WS_OUTPUT.O_TXN_CODE = DCRDCICH.TXN_CODE;
        WS_OUTPUT.O_CARD_NO = DCRDCICH.CARD_NO;
        WS_OUTPUT.O_TXN_AMT = DCRDCICH.TXN_AMT;
        WS_OUTPUT.O_TXN_CCY = DCRDCICH.TXN_CCY;
        WS_OUTPUT.O_TXN_TIME = DCRDCICH.TXN_TIME;
        WS_OUTPUT.O_DL_BR_NO = DCRDCICH.DL_BR_NO;
        WS_OUTPUT.O_FS_BR_NO = DCRDCICH.FS_BR_NO;
        WS_OUTPUT.O_MARKET_TYP = DCRDCICH.MARKET_TYP;
        WS_OUTPUT.O_SKJZD_NO = DCRDCICH.SKJZD_NO;
        WS_OUTPUT.O_SKF_NO = DCRDCICH.SKF_NO;
        WS_OUTPUT.O_SKF_ADDR = DCRDCICH.SKF_ADDR;
        WS_OUTPUT.O_FEE = DCRDCICH.FEE;
        WS_OUTPUT.O_CROSS_BORDER_FLAG = DCRDCICH.CROSS_BORDER_FLAG;
        WS_OUTPUT.O_TXN_DT = DCRDCICH.TXN_DT;
        WS_OUTPUT.O_CNTY_CODE = DCRDCICH.CNTY_CODE;
        WS_OUTPUT.O_TXN_TYPE = DCRDCICH.TXN_TYPE;
        WS_OUTPUT.O_AUT_AMT = DCRDCICH.AUT_AMT;
        WS_OUTPUT.O_ZD_TYPE = DCRDCICH.ZD_TYPE;
        WS_OUTPUT.O_REAL_AMT = DCRDCICH.REAL_AMT;
        WS_OUTPUT.O_RESL_STS = DCRDCICH.RESL_STS;
        WS_OUTPUT.O_LAST_STS = DCRDCICE.LAST_STS;
        WS_OUTPUT.O_CRT_DATE = DCRDCICH.CRT_DATE;
        WS_OUTPUT.O_CRT_TLR = DCRDCICH.CRT_TLR;
        WS_OUTPUT.O_UPDTBL_DATE = DCRDCICH.UPDTBL_DATE;
        WS_OUTPUT.O_UPDTBL_TLR = DCRDCICH.UPDTBL_TLR;
        WS_OUTPUT.O_BATCH_NO = DCRDCICH.KEY.BATCH_NO;
        WS_OUTPUT.O_BATCH_SN = DCRDCICH.KEY.BATCH_SN;
        CEP.TRC(SCCGWA, WS_OUTPUT.O_BATCH_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT.O_BATCH_SN);
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 293;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 293;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_03_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_REW;
        SCCFMT.DATA_LEN = 44;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTDCICE() throws IOException,SQLException,Exception {
        DCTDCICE_RD = new DBParm();
        DCTDCICE_RD.TableName = "DCTDCICE";
        DCTDCICE_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCICE, DCTDCICE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTDCICH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICH);
        DCRDCICH.KEY.CLEAR_DATE = DCRDCICE.KEY.CLEAR_DATE;
        DCRDCICH.KEY.BATCH_NO = DCRDCICE.KEY.BATCH_NO;
        DCRDCICH.KEY.BATCH_SN = DCRDCICE.KEY.BATCH_SN;
        DCTDCICH_RD = new DBParm();
        DCTDCICH_RD.TableName = "DCTDCICH";
        IBS.READ(SCCGWA, DCRDCICH, DCTDCICH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DCTDCICE_DT_NO() throws IOException,SQLException,Exception {
        WS_DB_VARS.SERIN_CLEAR_DATE = DCCS3310.CLEAR_DATE;
        WS_DB_VARS.SERIN_CARD_NO = DCCS3310.CARD_NO;
        DCTDCICE_BR.rp = new DBParm();
        DCTDCICE_BR.rp.TableName = "DCTDCICE";
        DCTDCICE_BR.rp.where = "CLEAR_DATE = :WS_DB_VARS.SERIN_CLEAR_DATE "
            + "AND CARD_NO = :WS_DB_VARS.SERIN_CARD_NO";
        IBS.STARTBR(SCCGWA, DCRDCICE, this, DCTDCICE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DCTDCICE_DT() throws IOException,SQLException,Exception {
        WS_DB_VARS.SERIN_CLEAR_DATE = DCCS3310.CLEAR_DATE;
        DCTDCICE_BR.rp = new DBParm();
        DCTDCICE_BR.rp.TableName = "DCTDCICE";
        DCTDCICE_BR.rp.where = "CLEAR_DATE = :WS_DB_VARS.SERIN_CLEAR_DATE";
        IBS.STARTBR(SCCGWA, DCRDCICE, this, DCTDCICE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_READNEXT_DCTDCICE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDCICE, this, DCTDCICE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_REWRITE_DCTDCICE() throws IOException,SQLException,Exception {
        DCRDCICE.LAST_STS = '1';
        DCRDCICE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCTDCICE_RD = new DBParm();
        DCTDCICE_RD.TableName = "DCTDCICE";
        IBS.REWRITE(SCCGWA, DCRDCICE, DCTDCICE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTDCICE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDCICE_BR);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
    }
    public void S000_CALL_DDZUTRF() throws IOException,SQLException,Exception {
        DDZUTRF DDZUTRF = new DDZUTRF();
        DDZUTRF.MP(SCCGWA, DDCUTRF);
        if (DDCUTRF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUTRF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
