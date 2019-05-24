package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFBVF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_PGM_NAME = "BPZFFBVF";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_F_FEE_CAL_VCHG = "BP-F-Z-CERT-CHARGE  ";
    String WS_ERR_MSG = " ";
    short WS_CNT1 = 0;
    long WS_RESP = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFEEUC BPCFEEUC = new BPCFEEUC();
    BPCBVCHG BPCBVCHG = new BPCBVCHG();
    SCCGWA SCCGWA;
    BPCFBVF BPCFBVF;
    BPCFFCAL BPCFFCAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFFCAL BPCFFCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFCAL = BPCFFCAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFFBVF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCFBVF = (BPCFBVF) BPCFFCAL.DATA.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.EXT_APP);
        B010_GET_FREE_FMT_INFO();
        S000_CALL_BPZFFCAL();
        B020_CAL_FEE_BY_NUM();
    }
    public void B010_GET_FREE_FMT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFEEUC);
        if (BPCFEEUC.TXT_DATA.TXT == null) BPCFEEUC.TXT_DATA.TXT = "";
        JIBS_tmp_int = BPCFEEUC.TXT_DATA.TXT.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFEEUC.TXT_DATA.TXT += " ";
        if (BPCFBVF.BVF == null) BPCFBVF.BVF = "";
        JIBS_tmp_int = BPCFBVF.BVF.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCFBVF.BVF += " ";
        BPCFEEUC.TXT_DATA.TXT = BPCFBVF.BVF + BPCFEEUC.TXT_DATA.TXT.substring(3);
        if (BPCFEEUC.TXT_DATA.TXT == null) BPCFEEUC.TXT_DATA.TXT = "";
        JIBS_tmp_int = BPCFEEUC.TXT_DATA.TXT.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFEEUC.TXT_DATA.TXT += " ";
        if (BPCFBVF.KIND == null) BPCFBVF.KIND = "";
        JIBS_tmp_int = BPCFBVF.KIND.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCFBVF.KIND += " ";
        BPCFEEUC.TXT_DATA.TXT = BPCFEEUC.TXT_DATA.TXT.substring(0, 4 - 1) + BPCFBVF.KIND + BPCFEEUC.TXT_DATA.TXT.substring(4 + 6 - 1);
        if (BPCFEEUC.TXT_DATA.TXT == null) BPCFEEUC.TXT_DATA.TXT = "";
        JIBS_tmp_int = BPCFEEUC.TXT_DATA.TXT.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFEEUC.TXT_DATA.TXT += " ";
        JIBS_tmp_str[0] = "" + BPCFBVF.FLAG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCFEEUC.TXT_DATA.TXT = BPCFEEUC.TXT_DATA.TXT.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPCFEEUC.TXT_DATA.TXT.substring(10 + 1 - 1);
        CEP.TRC(SCCGWA, BPCFEEUC.TXT_DATA.TXT);
        CEP.TRC(SCCGWA, BPCFEEUC.KEY.FREE_FMT_KEY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FREE_FMT);
    }
    public void B020_CAL_FEE_BY_NUM() throws IOException,SQLException,Exception {
        if (BPCFFCAL.DATA.TX_CNT != 0 
            && BPCFFCAL.DATA.CHG_AMT != 0) {
            BPCFFCAL.DATA.CHG_AMT = BPCFFCAL.DATA.CHG_AMT * BPCFFCAL.DATA.TX_CNT;
            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCFFCAL.DATA.TX_CNT;
        } else {
            BPCFFCAL.DATA.CHG_AMT = 0;
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void S000_CALL_BPZBVCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_FEE_CAL_VCHG, BPCBVCHG);
    }
    public void S000_CALL_BPZFFCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_CAL_FEE, BPCFFCAL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFFCAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFFCAL = ");
            CEP.TRC(SCCGWA, BPCFFCAL);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
