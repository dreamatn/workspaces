package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSAYIQ {
    DBParm DCTCDDAT_RD;
    DBParm DCTAPPLC_RD;
    brParm DCTAPPLC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC902";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 8;
    String K_HIS_REMARK = "THE APPLE PAY MANAGEMENT-QUERY";
    String K_HIS_COPYBOOK = "DCRAPPLC";
    String K_TBL_APPLC = "DCTAPPLC";
    short K_PAGE_ROW = 8;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    short WS_TOTAL_PAGE = 0;
    int WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_PAGE_ROW = 0;
    int WS_NEXT_START_NUM = 0;
    int WS_BAL_CNT = 0;
    short WS_IDX = 0;
    int WS_NUM = 0;
    int WS_START_NUM = 0;
    String WS_SPAN = " ";
    String WS_HOLDER_ID_TYPE = " ";
    String WS_HOLDER_ID_NO = " ";
    String WS_HOLDER_NAME = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    DCCF902 DCCF902 = new DCCF902();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9902 DCCS9902;
    public void MP(SCCGWA SCCGWA, DCCS9902 DCCS9902) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9902 = DCCS9902;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSAYIQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCF902);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_PROC();
        if (pgmRtn) return;
        B020_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9902.SPAN);
        WS_SPAN = DCCS9902.SPAN;
        if (DCCS9902.SPAN.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        IBS.init(SCCGWA, DCRAPPLC);
        IBS.init(SCCGWA, DCCF902.OUT_HEAD);
        T000_STARTBR_DCTAPPLC_NO();
        if (pgmRtn) return;
        T000_READNEXT_DCTAPPLC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG != 'N') {
            WS_IDX = 0;
            WS_TOTAL_NUM = 0;
            CEP.TRC(SCCGWA, WS_IDX);
            while (WS_TBL_FLAG != 'N' 
                && WS_IDX != 8) {
                B020_01_02_OUTPUT();
                if (pgmRtn) return;
                T000_READNEXT_DCTAPPLC();
                if (pgmRtn) return;
            }
            if (WS_TBL_FLAG == 'N') {
                WS_TOTAL_NUM = WS_IDX;
            }
        } else {
            WS_TOTAL_NUM = 0;
        }
        T000_ENDBR_DCTAPPLC();
        if (pgmRtn) return;
    }
    public void B020_DATA_OUTPUT() throws IOException,SQLException,Exception {
        DCCF902.OUT_HEAD.O_TOTAL_NUM = (short) WS_TOTAL_NUM;
        CEP.TRC(SCCGWA, DCCF902.OUT_HEAD.O_TOTAL_NUM);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF902;
        SCCFMT.DATA_LEN = 1377;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_01_02_OUTPUT() throws IOException,SQLException,Exception {
        WS_IDX += 1;
        CEP.TRC(SCCGWA, WS_IDX);
        IBS.init(SCCGWA, DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1]);
        CEP.TRC(SCCGWA, "N555");
        DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1].SPAN = DCRAPPLC.SPAN;
        DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1].MPAN = DCRAPPLC.MPAN;
        DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1].MPAN_ID = DCRAPPLC.MPAN_ID;
        DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1].SE_ID = DCRAPPLC.SE_ID;
        DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1].STS = DCRAPPLC.STS;
        DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1].EXP_DT = DCRAPPLC.EXP_DT;
        CEP.TRC(SCCGWA, DCCF902.OUT_HEAD.OUT_INFO[WS_IDX-1].MPAN);
        CEP.TRC(SCCGWA, "-----------OUTPUT-----------");
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        IBS.READ(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_UPD_DCTAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        DCTAPPLC_RD.upd = true;
        IBS.READ(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DCTAPPLC_ID() throws IOException,SQLException,Exception {
        DCTAPPLC_BR.rp = new DBParm();
        DCTAPPLC_BR.rp.TableName = "DCTAPPLC";
        DCTAPPLC_BR.rp.where = "( CARD_HOLDER_ID_TYPE = :WS_HOLDER_ID_TYPE ) "
            + "AND ( CARD_HOLDER_ID_NO = :WS_HOLDER_ID_NO ) "
            + "AND ( CARD_HOLDER_NAME = :WS_HOLDER_NAME )";
        IBS.STARTBR(SCCGWA, DCRAPPLC, this, DCTAPPLC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DCTAPPLC_NO() throws IOException,SQLException,Exception {
        DCTAPPLC_BR.rp = new DBParm();
        DCTAPPLC_BR.rp.TableName = "DCTAPPLC";
        DCTAPPLC_BR.rp.where = "SPAN = :WS_SPAN "
            + "AND ( STS = '01' "
            + "OR STS = '02' )";
        IBS.STARTBR(SCCGWA, DCRAPPLC, this, DCTAPPLC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READNEXT_DCTAPPLC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRAPPLC, this, DCTAPPLC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_ENDBR_DCTAPPLC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTAPPLC_BR);
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
