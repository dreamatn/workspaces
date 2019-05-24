package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUABRW {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTACLNK_RD;
    brParm DCTACLNK_BR = new brParm();
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTSPAC_RD;
    DBParm DCTIAACR_RD;
    boolean pgmRtn = false;
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    short K_PAGE_ROW = 200;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    String WS_AC = " ";
    short WS_TOTAL_PAGE = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_PAGE_ROW = 0;
    int WS_TOTAL_NUM = 0;
    int WS_NEXT_START_NUM = 0;
    int WS_BAL_CNT = 0;
    char WS_ACLNK_FLG = ' ';
    char WS_IAACR_FLG = ' ';
    char WS_SPAC_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCRSPAC DCRSPAC = new DCRSPAC();
    int WS_NUM = 0;
    int WS_START_NUM = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUABRW DCCUABRW;
    public void MP(SCCGWA SCCGWA, DCCUABRW DCCUABRW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUABRW = DCCUABRW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUABRW return!");
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
        if (DCCUABRW.INP_DATA.CARD_NO.trim().length() > 0) {
            B021_CARD_INQ_VIA_AC();
            if (pgmRtn) return;
            B022_VIA_AC_INQ_CARD();
            if (pgmRtn) return;
        }
        if (DCCUABRW.INP_DATA.AC.trim().length() > 0) {
            WS_AC = DCCUABRW.INP_DATA.AC;
            B022_VIA_AC_INQ_CARD();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, WS_AC);
            DCCUABRW.INP_DATA.AC = WS_AC;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCUABRW.INP_DATA.CARD_NO.trim().length() == 0 
            && DCCUABRW.INP_DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD, DCCUABRW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUABRW.INP_DATA.CARD_NO.trim().length() > 0 
            && DCCUABRW.INP_DATA.AC.trim().length() > 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_MISSING, DCCUABRW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_CARD_INQ_VIA_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = DCCUABRW.INP_DATA.CARD_NO;
        CEP.TRC(SCCGWA, DCRACLNK.KEY.CARD_NO);
        T000_READ_DCTACLNK();
        if (pgmRtn) return;
        if (WS_ACLNK_FLG == 'Y') {
            WS_AC = DCRACLNK.VIA_AC;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_NOT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_NOT_EXIST, DCCUABRW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRACLNK.VIA_AC);
    }
    public void B022_VIA_AC_INQ_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = WS_AC;
        CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
        B025_PAGE_CONDITION();
        if (pgmRtn) return;
        T00_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        T00_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
        CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
        if (WS_IAACR_FLG == 'Y') {
            WS_I = 0;
            WS_TOTAL_NUM = 0;
            while (WS_IAACR_FLG != 'N' 
                && WS_I < WS_PAGE_ROW) {
                WS_I = WS_I + 1;
                B022_01_OUTPUT_DATA();
                if (pgmRtn) return;
                WS_NEXT_START_NUM += 1;
                WS_START_NUM = WS_NEXT_START_NUM;
                T00_READNEXT_DCTIAACR();
                if (pgmRtn) return;
            }
            if (WS_IAACR_FLG == 'N') {
                CEP.TRC(SCCGWA, "--------------T--------");
                WS_TOTAL_PAGE = WS_CURR_PAGE;
                WS_BAL_CNT = WS_I;
                WS_TOTAL_NUM = ( WS_CURR_PAGE - 1 ) * WS_PAGE_ROW + WS_BAL_CNT;
                CEP.TRC(SCCGWA, WS_TOTAL_NUM);
                CEP.TRC(SCCGWA, WS_CURR_PAGE);
                CEP.TRC(SCCGWA, WS_BAL_CNT);
                WS_LAST_PAGE = 'Y';
                WS_PAGE_ROW = (short) WS_BAL_CNT;
            } else {
                CEP.TRC(SCCGWA, "--------------G--------");
                DCRIAACR.KEY.VIA_AC = DCRACLNK.VIA_AC;
                R000_GROUP_ALL();
                if (pgmRtn) return;
                WS_BAL_CNT = WS_TOTAL_NUM % WS_PAGE_ROW;
                WS_TOTAL_PAGE = (short) ((WS_TOTAL_NUM - WS_BAL_CNT) / WS_PAGE_ROW);
                if (WS_BAL_CNT != 0) {
                    WS_TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_TOTAL_PAGE = 1;
            WS_TOTAL_NUM = 0;
            WS_LAST_PAGE = 'Y';
            WS_PAGE_ROW = 0;
        }
        T00_ENDBR_DCTIAACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "----------------BEGIN OUTPUT----------");
        B030_OUTPUT_WRITE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "----------------END   OUTPUT----------");
    }
    public void B022_01_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        DCCUABRW.OUT_DATA[WS_I-1].SEQ = DCRIAACR.KEY.SEQ;
        DCCUABRW.OUT_DATA[WS_I-1].FRM_APP = DCRIAACR.FRM_APP;
        DCCUABRW.OUT_DATA[WS_I-1].SUB_AC = DCRIAACR.SUB_AC;
        if (DCRIAACR.STD_AC_FLG == 'N') {
            IBS.init(SCCGWA, DCRSPAC);
            DCRSPAC.STD_AC = DCRIAACR.SUB_AC;
            T000_READ_DCTSPAC();
            if (pgmRtn) return;
            if (WS_SPAC_FLAG == 'Y') {
                DCCUABRW.OUT_DATA[WS_I-1].SUB_AC = DCRSPAC.KEY.FREE_AC;
                CEP.TRC(SCCGWA, DCCUABRW.OUT_DATA[WS_I-1].SUB_AC);
            }
        }
        DCCUABRW.OUT_DATA[WS_I-1].AC_CCY = DCRIAACR.CCY;
        DCCUABRW.OUT_DATA[WS_I-1].CCY_TYPE = DCRIAACR.CCY_TYPE;
        DCCUABRW.OUT_DATA[WS_I-1].DEFAULT_FLG = DCRIAACR.DEFAULT_FLG;
        DCCUABRW.OUT_DATA[WS_I-1].VCH_TYPE = DCRIAACR.VCH_TYPE;
        DCCUABRW.OUT_DATA[WS_I-1].VCH_CD = DCRIAACR.VCH_ID;
        DCCUABRW.OUT_DATA[WS_I-1].VCH_NO = DCRIAACR.VCH_NO;
        DCCUABRW.OUT_DATA[WS_I-1].AC_USAGE = DCRIAACR.USAGE;
    }
    public void B025_PAGE_CONDITION() throws IOException,SQLException,Exception {
        if (DCCUABRW.INP_DATA.PAGE_NUM == 0) {
            WS_CURR_PAGE = 1;
        } else {
            WS_CURR_PAGE = (short) DCCUABRW.INP_DATA.PAGE_NUM;
        }
        DCCUABRW.OUT_PAGE.CURR_PAGE = WS_CURR_PAGE;
        WS_LAST_PAGE = 'N';
        if (DCCUABRW.INP_DATA.ROWS == 0) {
            WS_PAGE_ROW = K_PAGE_ROW;
        } else {
            if (DCCUABRW.INP_DATA.ROWS > K_PAGE_ROW) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ERR_PAGE_ROW;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ERR_PAGE_ROW, DCCUABRW.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_PAGE_ROW = DCCUABRW.INP_DATA.ROWS;
            }
        }
        WS_NEXT_START_NUM = ( ( WS_CURR_PAGE - 1 ) * WS_PAGE_ROW ) + 1;
        WS_START_NUM = WS_NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_START_NUM);
    }
    public void B030_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        DCCUABRW.OUT_PAGE.TOTAL_PAGE = WS_TOTAL_PAGE;
        DCCUABRW.OUT_PAGE.TOTAL_NUM = WS_TOTAL_NUM;
        DCCUABRW.OUT_PAGE.LAST_PAGE = WS_LAST_PAGE;
        DCCUABRW.OUT_PAGE.PAGE_ROW = WS_PAGE_ROW;
        CEP.TRC(SCCGWA, DCCUABRW.OUT_PAGE.TOTAL_PAGE);
        CEP.TRC(SCCGWA, DCCUABRW.OUT_PAGE.TOTAL_NUM);
        CEP.TRC(SCCGWA, DCCUABRW.OUT_PAGE.LAST_PAGE);
        CEP.TRC(SCCGWA, DCCUABRW.OUT_PAGE.PAGE_ROW);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCINF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUABRW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTACLNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRACLNK.KEY.CARD_NO);
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACLNK_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACLNK_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ     DCTACLNK ERROR";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTACLNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRACLNK.KEY.CARD_NO);
        DCTACLNK_BR.rp = new DBParm();
        DCTACLNK_BR.rp.TableName = "DCTACLNK";
        DCTACLNK_BR.rp.where = "CARD_NO = :DCRACLNK.KEY.CARD_NO";
        DCTACLNK_BR.rp.order = "CARD_AC_STATUS";
        IBS.STARTBR(SCCGWA, DCRACLNK, this, DCTACLNK_BR);
    }
    public void T000_READNEXT_DCTACLNK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRACLNK, this, DCTACLNK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "DATA FOUND");
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "DATA NOT FOUND");
        }
    }
    public void T000_ENDBR_DCTACLNK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTACLNK_BR);
    }
    public void T00_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND ACCR_FLG = '1'";
        DCTIAACR_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR DCTIAACR ERROR";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        DCTSPAC_RD.where = "STD_AC = :DCRSPAC.STD_AC";
        IBS.READ(SCCGWA, DCRSPAC, this, DCTSPAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "DCTSPAC DATA FOUND");
            WS_SPAC_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "DCTSPAC  DATA NOT FOUND");
            WS_SPAC_FLAG = 'N';
        }
    }
    public void T00_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IAACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IAACR_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READNEXT DCTIAACR ERROR";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ENDBE DCTIAACR ERROR";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.set = "WS-NUM=COUNT(*)";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND ACCR_FLG = '1'";
        IBS.GROUP(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        WS_TOTAL_NUM = WS_NUM;
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUABRW.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUABRW=");
            CEP.TRC(SCCGWA, DCCUABRW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
