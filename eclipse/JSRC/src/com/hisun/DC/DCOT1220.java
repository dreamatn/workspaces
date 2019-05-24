package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1220 {
    DBParm DCTSASBH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_HIS_RMK = "ORAL REPORTING OF LOSE";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String K_OUTPUT_FMT = "DC120";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCOT1220_WS_OUTPUT WS_OUTPUT = new DCOT1220_WS_OUTPUT();
    char WS_SASB_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRSASBH DCRSASBH = new DCRSASBH();
    String WS_SASB_VCH_NO_HI = " ";
    String WS_SASB_VCH_NO_LOW = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB1220_AWA_1220 DCB1220_AWA_1220;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1220 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1220_AWA_1220>");
        DCB1220_AWA_1220 = (DCB1220_AWA_1220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_SASB_AC_INFO();
        if (pgmRtn) return;
        B030_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB1220_AWA_1220.SASB_NO);
        if (DCB1220_AWA_1220.SASB_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SASB_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_SASB_AC_INFO() throws IOException,SQLException,Exception {
        DCRSASBH.KEY.SASB_AC = DCB1220_AWA_1220.SASB_NO;
        DCRSASBH.KEY.SASB_VCH_NO = DCB1220_AWA_1220.VCH_NO;
        if (DCRSASBH.KEY.SASB_VCH_NO.trim().length() == 0) {
            T000_READ_DCTSASBH_DD();
            if (pgmRtn) return;
        } else {
            T000_READ_DCTSASBH();
            if (pgmRtn) return;
        }
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        CEP.TRC(SCCGWA, WS_SASB_FLG);
        if (WS_SASB_FLG == 'N') {
            WS_OUTPUT.WS_CARD_NO = DCB1220_AWA_1220.SASB_NO;
        } else {
            WS_OUTPUT.WS_CARD_NO = DCRSASBH.CARD_NO;
            WS_OUTPUT.WS_AC_TYP = DCRSASBH.AC_TYP;
            WS_OUTPUT.WS_CHNG_FLG = DCRSASBH.CHANGE_FLG;
            WS_OUTPUT.WS_OPEN_BR = DCRSASBH.AC_BR;
            CEP.TRC(SCCGWA, DCRSASBH.AC_BR);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_OPEN_BR);
        }
        CEP.TRC(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 27;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTSASBH_DD() throws IOException,SQLException,Exception {
        DCTSASBH_RD = new DBParm();
        DCTSASBH_RD.TableName = "DCTSASBH";
        DCTSASBH_RD.col = "SASB_AC, SASB_VCH_NO, CARD_NO, AC_NO, CHANGE_FLG, AC_TYP, AC_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTSASBH_RD.where = "SASB_AC = :DCRSASBH.KEY.SASB_AC "
            + "AND AC_TYP < > 'D'";
        DCTSASBH_RD.fst = true;
        DCTSASBH_RD.order = "AC_TYP DESC";
        IBS.READ(SCCGWA, DCRSASBH, this, DCTSASBH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SASB_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SASB_FLG = 'N';
        }
    }
    public void T000_READ_DCTSASBH() throws IOException,SQLException,Exception {
        DCTSASBH_RD = new DBParm();
        DCTSASBH_RD.TableName = "DCTSASBH";
        DCTSASBH_RD.col = "SASB_AC, SASB_VCH_NO, CARD_NO, AC_NO, CHANGE_FLG, AC_TYP, AC_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTSASBH_RD.where = "SASB_AC = :DCRSASBH.KEY.SASB_AC "
            + "AND SASB_VCH_NO = :DCRSASBH.KEY.SASB_VCH_NO";
        IBS.READ(SCCGWA, DCRSASBH, this, DCTSASBH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SASB_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SASB_FLG = 'N';
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
