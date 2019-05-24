package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLPE {
    DBParm BPTINVT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_REC_INVT = "BP-R-PROC-INVT ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRINVT BPRINVT = new BPRINVT();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCTINVT BPCTINVT = new BPCTINVT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSTLPE BPCSTLPE;
    public void MP(SCCGWA SCCGWA, BPCSTLPE BPCSTLPE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLPE = BPCSTLPE;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTLPE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*** A000 START ***");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*** B000 START ***");
        B100_FIRST_QUERY_DAY();
        if (pgmRtn) return;
        B200_CHECK_PREV_LOGIN_TIME();
        if (pgmRtn) return;
    }
    public void B100_FIRST_QUERY_DAY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*** B100 START ***");
        IBS.init(SCCGWA, BPRINVT);
        IBS.init(SCCGWA, BPCTINVT);
        BPRINVT.TX_TLR = BPCSTLPE.TX_DATA.TLR;
        CEP.TRC(SCCGWA, BPRINVT.TX_TLR);
        BPRINVT.PLBOX_TYPE = '1';
        BPTINVT_RD = new DBParm();
        BPTINVT_RD.TableName = "BPTINVT";
        BPTINVT_RD.where = "TX_TLR = :BPRINVT.TX_TLR "
            + "AND INVNTYP = :BPRINVT.PLBOX_TYPE";
        BPTINVT_RD.fst = true;
        BPTINVT_RD.order = "DATE DESC";
        IBS.READ(SCCGWA, BPRINVT, this, BPTINVT_RD);
        CEP.TRC(SCCGWA, BPRINVT.KEY.DATE);
    }
    public void B200_CHECK_PREV_LOGIN_TIME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.CUR_TLR = BPCSTLPE.TX_DATA.TLR;
        BPRVHPB.POLL_BOX_IND = '1';
        BPRVHPB.STS = 'N';
        BPCRVHPB.INFO.FUNC = 'F';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTLVB);
        if (BPCSTLPE.TX_DATA.TLR.trim().length() > 0) {
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLVB.PLBOX_TP = '2';
            BPRTLVB.CRNT_TLR = BPCSTLPE.TX_DATA.TLR;
            BPCRTLVB.INFO.FUNC = 'T';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BPRINVT.KEY.DATE;
        SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        if (SCCCLDT.DATE1 != 0 
            && (BPCRVHPB.RETURN_INFO == 'F' 
            || BPCRTLVB.RETURN_INFO == 'F')) {
            SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
            SCSSCLDT1.MP(SCCGWA, SCCCLDT);
            if (SCCCLDT.RC != 0) {
                WS_ERR_MSG = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            if (SCCCLDT.DAYS > 30) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_30_DAYS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRINVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_INVT, BPCTINVT);
        if (BPCTINVT.RETURN_INFO == 'N') {
            WS_ERR_MSG = "NOTFND";
        }
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
