package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSKBID {
    DBParm DDTFBID_RD;
    DBParm DDTHLDR_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD565";
    String K_HIS_REMARKS = "KEEP DEPOSIT FBID";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    char WS_FBID_STS = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DDCOKBID DDCOKBID = new DDCOKBID();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRFBID DDRFBID = new DDRFBID();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    DDCSKBID DDCSKBID;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSKBID DDCSKBID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSKBID = DDCSKBID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSKBID return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_INQ_HLD_INF_PROC();
        if (pgmRtn) return;
        B030_INQ_ACAC_PROC();
        if (pgmRtn) return;
        B040_UPDATE_DDTFBID();
        if (pgmRtn) return;
        B050_WRITE_DDTHLDR();
        if (pgmRtn) return;
        B060_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        B070_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSKBID.DATA.REF_NO);
        CEP.TRC(SCCGWA, DDCSKBID.DATA.NEW_DT);
        if (DDCSKBID.DATA.REF_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT);
        }
        if (DDCSKBID.DATA.NEW_DT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DATE_MUST_INPUT);
        }
    }
    public void B020_INQ_HLD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        DDRFBID.KEY.REF_NO = DDCSKBID.DATA.REF_NO;
        T000_READ_DDTFBID();
        if (pgmRtn) return;
        WS_EFF_DATE = DDRFBID.EFF_DATE;
        WS_EXP_DATE = DDRFBID.EXP_DATE;
        WS_FBID_STS = DDRFBID.STS;
        CEP.TRC(SCCGWA, DDRFBID.STS);
        if (DDRFBID.STS == '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_FBD_CR_REC_REL);
        }
        if (DDCSKBID.DATA.CHG_BR == 0) {
            DDCSKBID.DATA.CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CEP.TRC(SCCGWA, DDRFBID.ORG_TYP);
        if (DDRFBID.ORG_TYP == '2') {
            if (DDCSKBID.DATA.CHG_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_BOOK_NO_M_INPUT);
            }
            if (DDCSKBID.DATA.SPR_NM.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ORG_NAME_M_INPUT);
            }
            if (DDCSKBID.DATA.LAW_NM1.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAW_NM_M_INPUT);
            }
            if (DDCSKBID.DATA.LAW_NO1.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAW_NO_M_INPUT);
            }
        }
        CEP.TRC(SCCGWA, DDCSKBID.DATA.NEW_DT);
        if (WS_EXP_DATE >= DDCSKBID.DATA.NEW_DT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FBID_NEW_DT_ERR);
        }
        if (SCCGWA.COMM_AREA.AC_DATE > WS_EXP_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BID_EXPDT_LESS_GWADT);
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = DDCSKBID.DATA.NEW_DT;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, DDCSKBID.DATA.NEW_DT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_HLD_EXP_DT_INVALID);
        }
        CEP.TRC(SCCGWA, WS_EFF_DATE);
        CEP.TRC(SCCGWA, WS_EXP_DATE);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_EXP_DATE;
        SCCCLDT.MTHS = 12;
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, DDCSKBID.DATA.NEW_DT);
        if (DDCSKBID.DATA.NEW_DT > SCCCLDT.DATE2) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FBID_TERM_OVER_YEAR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
    }
    public void B030_INQ_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = DDRFBID.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_STS == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, "DD");
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, "DC");
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = DDRFBID.AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == 'R' 
                || TDRSMST.ACO_STS == '1' 
                || TDRSMST.ACO_STS == '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = DDRFBID.AC;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_DDTFBID() throws IOException,SQLException,Exception {
        DDRFBID.EXP_DATE = DDCSKBID.DATA.NEW_DT;
        DDRFBID.ORG_NAME = DDCSKBID.DATA.SPR_NM;
        DDRFBID.BOOK_NO = DDCSKBID.DATA.CHG_NO;
        DDRFBID.SLAW_NM1 = DDCSKBID.DATA.LAW_NM1;
        DDRFBID.SLAW_NO1 = DDCSKBID.DATA.LAW_NO1;
        DDRFBID.SLAW_NM2 = DDCSKBID.DATA.LAW_NM2;
        DDRFBID.SLAW_NO2 = DDCSKBID.DATA.LAW_NO2;
        DDRFBID.REASON = DDCSKBID.DATA.RMK;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTFBID();
        if (pgmRtn) return;
    }
    public void B050_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DDCSKBID.DATA.REF_NO;
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.AC = DDRFBID.AC;
        DDRHLDR.HLD_TYP = 'C';
        DDRHLDR.CHG_RSN = DDCSKBID.DATA.RMK;
        DDRHLDR.SPR_BR_TYP = DDRFBID.ORG_TYP;
        DDRHLDR.SPR_BR_NM = DDCSKBID.DATA.SPR_NM;
        DDRHLDR.CHG_WRIT_NO = DDCSKBID.DATA.CHG_NO;
        DDRHLDR.LAW_OFF_NM1 = DDCSKBID.DATA.LAW_NM1;
        DDRHLDR.LAW_ID_NO1 = DDCSKBID.DATA.LAW_NO1;
        DDRHLDR.LAW_OFF_NM2 = DDCSKBID.DATA.LAW_NM2;
        DDRHLDR.LAW_ID_NO2 = DDCSKBID.DATA.LAW_NO2;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void B060_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.REF_NO = DDCSKBID.DATA.REF_NO;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "DDRHLD";
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CCY);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CI_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOKBID);
        DDCOKBID.DATA.REF_NO = DDCSKBID.DATA.REF_NO;
        DDCOKBID.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DDCOKBID.DATA.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        DDCOKBID.DATA.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCOKBID.DATA.CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        DDCOKBID.DATA.SPR_TYP = DDRFBID.ORG_TYP;
        DDCOKBID.DATA.EFF_DT = WS_EFF_DATE;
        DDCOKBID.DATA.EXP_DT = WS_EXP_DATE;
        DDCOKBID.DATA.NEW_DT = DDCSKBID.DATA.NEW_DT;
        DDCOKBID.DATA.CHG_NO = DDCSKBID.DATA.CHG_NO;
        DDCOKBID.DATA.SPR_NM = DDCSKBID.DATA.SPR_NM;
        DDCOKBID.DATA.RMK = DDCSKBID.DATA.RMK;
        DDCOKBID.DATA.LAW_NM1 = DDCSKBID.DATA.LAW_NM1;
        DDCOKBID.DATA.LAW_NO1 = DDCSKBID.DATA.LAW_NO1;
        DDCOKBID.DATA.LAW_NM2 = DDCSKBID.DATA.LAW_NM2;
        DDCOKBID.DATA.LAW_NO2 = DDCSKBID.DATA.LAW_NO2;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOKBID;
        SCCFMT.DATA_LEN = 1170;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTFBID() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.upd = true;
        IBS.READ(SCCGWA, DDRFBID, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FBID_REC_NOTFND);
        }
    }
    public void T000_REWRITE_DDTFBID() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        IBS.REWRITE(SCCGWA, DDRFBID, DDTFBID_RD);
    }
    public void T000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.WRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_HLDR_REC_EXIST);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
